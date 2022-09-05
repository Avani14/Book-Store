package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(BookDTO bookDTO);
    Book editBook(BookDTO bookDTO,long id);
    String deleteBook(long id);
    Book getBookByID(long id);
    List<Book> getAllBooks();
    Book changeBookQty(long id,int qty);
    Book changeBookPrice(long id,double price);
}
