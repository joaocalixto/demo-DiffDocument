package com.example.scalableweb.demo.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.scalableweb.demo.data.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
	
	List<Document> findByOperantionId(Long id);

}
