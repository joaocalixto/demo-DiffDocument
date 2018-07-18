package com.example.scalableweb.demo.service;

import java.util.List;

import com.example.scalableweb.demo.data.entity.Document;

public interface StoreDocumentService {
	
	List<Document> findByOperantionId(Long id);
	void saveDocument(Document document);

}
