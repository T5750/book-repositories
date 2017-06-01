package com.evangel.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evangel.model.Reader;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}
