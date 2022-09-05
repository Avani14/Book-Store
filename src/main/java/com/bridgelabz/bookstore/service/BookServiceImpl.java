package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Book addBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO,Book.class);
        log.info("Book added is:"+book);
        return bookRepository.save(book);
    }

    @Override
    public Book editBook(BookDTO bookDTO, long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            return null;
        }
        book.setBook_description(bookDTO.getBook_description());
        book.setBook_author(bookDTO.getBook_author());
        book.setBook_name(bookDTO.getBook_name());
        book.setPrice(bookDTO.getPrice());
        book.setQuantity(bookDTO.getQuantity());
        book.setLogo_multiPart(bookDTO.getLogo_multiPart());
        return bookRepository.save(book);
    }

    @Override
    public String deleteBook(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            return null;
        }
        bookRepository.deleteById(id);
        return "Book with id:"+id+" deleted successfully!";
    }

    @Override
    public Book getBookByID(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new NullPointerException();
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if(books == null){
            throw new NullPointerException();
        }
        return books;
    }

    @Override
    public Book changeBookQty(long id, int qty) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new NullPointerException();
        }
        book.setQuantity(qty);
        return bookRepository.save(book);
    }

    @Override
    public Book changeBookPrice(long id, double price) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book == null){
            throw new NullPointerException();
        }
        book.setPrice(price);
        return bookRepository.save(book);
    }
}
