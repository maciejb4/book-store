package pl.jstk.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jstk.constants.ViewNames;
import pl.jstk.service.BookService;
import pl.jstk.to.BookTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookTo>> findAllBooks(){
        List<BookTo> allBooks = bookService.findAllBooks();
        return ResponseEntity.ok().body(allBooks);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookTo> findBook(@PathVariable("id") Long id){
        if(id < 0){
            return ResponseEntity.badRequest().body(null);
        }
        BookTo book = bookService.findById(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping("/books")
    public ResponseEntity<BookTo> addBook(@RequestBody BookTo bookTo){
        BookTo book = bookService.saveBook(bookTo);
        return ResponseEntity.ok().body(book);
    }

 }
