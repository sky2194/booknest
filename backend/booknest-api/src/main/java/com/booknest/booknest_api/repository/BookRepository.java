package com.booknest.booknest_api.repository;

import com.booknest.booknest_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

