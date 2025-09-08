package com.example.demo.controller;


import com.example.demo.Entities.Author;
import com.example.demo.Entities.Book;
import com.example.demo.Repositories.AuthorRepository;
import com.example.demo.Repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Page<Book> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            Pageable pageable
    ) {
        Specification<Book> spec = Specification.where(
                (root, query, cb) -> title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%")
        );

        if (priceMin != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), priceMin));
        }
        if (priceMax != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), priceMax));
        }

        return bookRepository.findAll(spec, pageable);
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Book create(@RequestBody Book book) {
        Author author = authorRepository.findById(book.getAuthor().getId()).orElseThrow();
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book newBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(newBook.getTitle());
                    book.setIsbn(newBook.getIsbn());
                    book.setPrice(newBook.getPrice());
                    book.setPublishedDate(newBook.getPublishedDate());
                    book.setAuthor(authorRepository.findById(newBook.getAuthor().getId()).orElseThrow());
                    return bookRepository.save(book);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}

