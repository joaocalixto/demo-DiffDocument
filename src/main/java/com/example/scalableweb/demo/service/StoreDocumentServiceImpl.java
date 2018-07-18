package com.example.scalableweb.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scalableweb.demo.data.entity.Document;
import com.example.scalableweb.demo.data.repo.DocumentRepository;

@Service
public class StoreDocumentServiceImpl implements StoreDocumentService{
	
	private DocumentRepository documentRepository;

	@Autowired
	public StoreDocumentServiceImpl(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@Override
	public List<Document>  findByOperantionId(Long id) {
		return documentRepository.findByOperantionId(id);
	}

	@Override
	public void saveDocument(Document document) {
		documentRepository.saveAndFlush(document);
		
	}
	

}
