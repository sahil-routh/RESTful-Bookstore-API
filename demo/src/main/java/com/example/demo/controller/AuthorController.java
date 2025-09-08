package com.example.demo.controller;

import com.example.demo.Entities.Author;
import com.example.demo.Repositories.AuthorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable Long id) {
        return authorRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Author create(@RequestBody Author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author newAuthor) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(newAuthor.getName());
                    author.setBio(newAuthor.getBio());
                    return authorRepository.save(author);
                })
                .orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorRepository.deleteById(id);
    }
}

