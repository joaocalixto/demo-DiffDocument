package com.example.scalableweb.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import com.example.scalableweb.demo.api.v1.controller.model.DiffDocumentResponse;
import com.example.scalableweb.demo.data.entity.Document;

public class DefaultEntityUtils {
	
	public Document sampleDocumentA() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sampleDocA.txt").getFile());
		byte[] readAllBytes;
		Document document = new Document();
		
		try {
			readAllBytes = Files.readAllBytes(file.toPath());
			document.setOperantionId(1l);
			document.setContentBase64(Base64.encodeBase64(readAllBytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	public Document sampleDocumentB() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sampleDocB.txt").getFile());
		byte[] readAllBytes;
		Document document = new Document();
		
		try {
			readAllBytes = Files.readAllBytes(file.toPath());
			document.setOperantionId(1l);
			document.setContentBase64(Base64.encodeBase64(readAllBytes));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return document;
	}
	
	public Document sampleEmptyDocument() {

		byte[] readAllBytes;
		Document document = new Document();

		readAllBytes = new String("").getBytes();
		document.setOperantionId(1l);
		document.setContentBase64(Base64.encodeBase64(readAllBytes));

		return document;
	}	
	
	public String sampleStringDocumentA() {
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sampleDocA.txt").getFile());
		byte[] readAllBytes;
		String fileString = "";
		
		try {
			readAllBytes = Files.readAllBytes(file.toPath());
			fileString = new String(readAllBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileString;
	}
	
	public List<Document> sampleListEqualDocuments() {
		
		List<Document> listDocument = new ArrayList<Document>();
		
		listDocument.add(sampleDocumentA());
		listDocument.add(sampleDocumentA());
		
		return listDocument;
	}
	
	public List<Document> sampleListDifferentDocuments() {
		
		List<Document> listDocument = new ArrayList<Document>();
		
		listDocument.add(sampleDocumentA());
		listDocument.add(sampleDocumentB());
		
		return listDocument;
	}
	
	public static DiffDocumentResponse sampleServiceCompareOK() {
		DiffDocumentResponse diffDocumentResponse = new DiffDocumentResponse();
		diffDocumentResponse.setStatus("OK");
		diffDocumentResponse.setEqual(true);
		
		return diffDocumentResponse;
	}
	

}
