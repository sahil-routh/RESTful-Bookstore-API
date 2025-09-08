package com.example.demo.Repositories;
import com.example.demo.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AuthorRepository extends JpaRepository<Author, Long> {}

