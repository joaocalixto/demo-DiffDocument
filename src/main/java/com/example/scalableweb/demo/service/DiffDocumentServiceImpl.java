package com.example.scalableweb.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.scalableweb.demo.data.entity.Document;

@Service
public class DiffDocumentServiceImpl implements DiffDocumentService{
	
	private StoreDocumentService storeDocumentService;

	@Autowired
	public DiffDocumentServiceImpl(StoreDocumentService storeDocumentService) {
		this.storeDocumentService = storeDocumentService;
	}

	@Override
	public void left(Document document) {
		storeDocumentService.saveDocument(document);
	}

	@Override
	public void right(Document document) {
		storeDocumentService.saveDocument(document);
	}

	@Override
	public boolean compare(long operationId) {
		List<Document> findByOperantionId = storeDocumentService.findByOperantionId(operationId);
		

		return Arrays.equals(findByOperantionId.get(0).getContentBase64(), findByOperantionId.get(1).getContentBase64());
	}
	
	

}
