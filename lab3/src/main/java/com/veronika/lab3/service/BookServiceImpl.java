package com.veronika.lab3.service;

import com.veronika.lab3.db.model.Book;
import com.veronika.lab3.db.dao.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Iterable<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        try {
            bookRepository.deleteById(id);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            //for the reason of idempotency leave this blank
        }
    }

    @Override
    public Book add(String title, String author) {
        return bookRepository.save(new Book(title, author));
    }

    @Override
    public Book getById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Integer id, String title, String author) {
        Book bookFromDB = bookRepository.findById(id).get();
        bookFromDB.setTitle(title);
        bookFromDB.setAuthor(author);
        bookRepository.save(bookFromDB);
    }

}
