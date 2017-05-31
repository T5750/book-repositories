package com.evangel.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evangel.model.Book;

public interface ReadingListRepository extends JpaRepository<Book, Long> {
	List<Book> findByReader(String reader);
}
