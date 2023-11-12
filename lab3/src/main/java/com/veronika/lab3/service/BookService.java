package com.veronika.lab3.service;

import com.veronika.lab3.db.model.Book;

public interface BookService {

    Iterable<Book> listAll();

    void delete(Integer id);
    
    Book add(String title, String author);
    
    Book getById(Integer id);

    void update(Integer id, String title, String author);

}
