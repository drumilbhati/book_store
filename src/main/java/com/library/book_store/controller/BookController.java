package com.library.book_store.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.book_store.entity.BookEntity;
import com.library.book_store.service.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookEntity> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public BookEntity getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/book")
    public ResponseEntity<BookEntity> addBookById(@RequestBody BookEntity book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }
}
