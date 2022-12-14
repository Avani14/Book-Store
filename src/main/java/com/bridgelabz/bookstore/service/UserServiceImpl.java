package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.ResetDTO;
import com.bridgelabz.bookstore.dto.UserDTO;
import com.bridgelabz.bookstore.dto.VerifyDTO;
import com.bridgelabz.bookstore.exceptions.UserNotFound;
import com.bridgelabz.bookstore.exceptions.VerificationFailed;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.utility.OTPGeneration;
import com.bridgelabz.bookstore.utility.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private OTPGeneration otpGeneration;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Override
    public User getUserById(String id) {
        return userRepository.findUserByEmail(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        if(userRepository.findUserByEmail(user.getEmail()) != null){
            return null;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        int otp = otpGeneration.generateOTP();
        user.setVerify(false);
        user.setOtp(otp);
        emailSenderService.sendEmail(user.getEmail(), "You have been successfully registered to Book Store","Your OTP is:"+otp);
        return userRepository.save(user);
    }

    @Override
    public User editUser(UserDTO userDTO, long id,String auth) throws UserNotFound, VerificationFailed {
        User user = userRepository.findById(id).orElse(null);
        log.info(tokenUtility.decodeToken(auth));
        if(userRepository.findById(id) == null&& getUserById(tokenUtility.decodeToken(auth)) == null){
            if(userRepository.findById(id) == null) {
                throw new UserNotFound("User with id" + id + " not found");
            }
            else{
                throw new VerificationFailed("Verification failed");
            }
        }
        user.setDOB(userDTO.getDOB());
        user.setKYC(userDTO.getKYC());
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(long id,String auth) throws UserNotFound, VerificationFailed {
        if(userRepository.findById(id) == null&& getUserById(tokenUtility.decodeToken(auth)) == null){
            if(userRepository.findById(id) == null) {
                throw new UserNotFound("User with id" + id + " not found");
            }
            else{
                throw new VerificationFailed("Verification failed");
            }
        }
        userRepository.deleteById(id);
        return "User with id:"+id+" is deleted successfully!!";
    }

    @Override
    public String login(LoginDTO loginDTO) throws UserNotFound {
        User user = userRepository.findUserByEmail(loginDTO.getEmailID());
        if(user != null){
            if(bCryptPasswordEncoder.matches(loginDTO.getPassword(),user.getPassword())) {
                if (user.isVerify() == true) {
                    return tokenUtility.createToken(loginDTO.getEmailID());
                }
            }
        }
        else {
            throw new UserNotFound("User not found!!");
        }
        return null;
    }

    @Override
    public boolean verifyUser(String email, int otp) throws UserNotFound {
        User user = userRepository.findUserByEmail(email);
        log.info("Email"+email);
        log.info("OTP"+otp);
        log.info("User is "+user);
        if(user.getOtp() == otp && user != null && user.isVerify() == false){
            user.setVerify(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String resetPassword(ResetDTO resetDTO, String auth) throws UserNotFound, VerificationFailed {
        User user = userRepository.findUserByEmail(resetDTO.getEmail());
        log.info("The password"+bCryptPasswordEncoder.matches(resetDTO.getOld_pass(),user.getPassword()));
        log.info("User:"+userRepository.findUserByEmailAndPassword(resetDTO.getEmail(),resetDTO.getOld_pass()));
        if(user == null && userRepository.findUserByEmail(tokenUtility.decodeToken(auth)) == null && bCryptPasswordEncoder.matches(resetDTO.getOld_pass(),user.getPassword())){
               if(user == null){
                   throw new UserNotFound("User with id:"+resetDTO.getEmail()+" not found");
               }
               else{
                   throw new VerificationFailed("Verification failed");
               }
        }
        user.setPassword(bCryptPasswordEncoder.encode(resetDTO.getNew_pass()));
        userRepository.save(user);
        return "Password reset successful";
    }

    @Override
    public String forgetPassword(LoginDTO loginDTO, String auth) throws UserNotFound, VerificationFailed {
        User user = userRepository.findUserByEmail(loginDTO.getEmailID());
        if(user == null && userRepository.findUserByEmail(tokenUtility.decodeToken(auth)) == null){
            if(user == null){
                throw new UserNotFound("User with id:"+loginDTO.getEmailID()+" not found");
            }
            else{
                throw new VerificationFailed("Verification failed");
            }
        }
        user.setPassword(bCryptPasswordEncoder.encode(loginDTO.getPassword()));
        userRepository.save(user);
        return "Password reset successful";
    }


}
