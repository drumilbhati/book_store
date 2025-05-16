package com.library.book_store.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;

import com.library.book_store.entity.Book;
import com.library.book_store.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/book/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/book")
    public ResponseEntity<Book> addBookById(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.save(book));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBook(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("file") MultipartFile file) {
        try {
            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Only PDF files are accepted.");
            }

            bookService.save(new Book(author, title, file));
            return ResponseEntity.status(HttpStatus.OK);
        } finally {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY);
        }
    }
}
