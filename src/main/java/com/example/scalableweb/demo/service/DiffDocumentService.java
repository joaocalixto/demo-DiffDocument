package com.example.scalableweb.demo.service;

import com.example.scalableweb.demo.data.entity.Document;

public interface DiffDocumentService {
	
	void left(Document document);
	void right(Document document);
	boolean compare(long document);

}
