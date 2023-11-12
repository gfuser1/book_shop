package com.veronika.lab3.rest;

import com.veronika.lab3.db.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.veronika.lab3.service.BookService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("public/rest/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> browse() {
        return ResponseEntity.ok(bookService.listAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Integer id) {
        try {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getOne(@PathVariable("id") Integer id) {
        try {
            Book book = bookService.getById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        }catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{title}/{author}", method = RequestMethod.POST)
    public ResponseEntity<Object> add(@PathVariable("title") String title, @PathVariable("author") String author) {
        return ResponseEntity.ok(bookService.add(title, author));
    }

    @RequestMapping(value = "/{id}/{title}/{author}", method = RequestMethod.POST)
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @PathVariable("title") String title, @PathVariable("author") String author) {
        bookService.update(id,title,author);
        return new ResponseEntity<>(bookService.getById(id), HttpStatus.OK);
    }

    

}
