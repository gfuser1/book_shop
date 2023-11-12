package com.veronika.lab3.db.model;

import lombok.*;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Books")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //аннотация показывает, что аннотируемое поле является первичным ключом.
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="book_title")
    private String title;

    @Column(name="book_author")
    private String author;

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }


}
