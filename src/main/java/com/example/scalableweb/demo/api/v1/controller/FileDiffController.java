package com.example.scalableweb.demo.api.v1.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.scalableweb.demo.api.v1.controller.exception.BadRequestException;
import com.example.scalableweb.demo.api.v1.controller.model.DiffDocumentResponse;
import com.example.scalableweb.demo.api.v1.controller.model.ServiceResponse;
import com.example.scalableweb.demo.data.entity.Document;
import com.example.scalableweb.demo.service.DiffDocumentService;

@RestController
@Validated
@RequestMapping(path = "/api/v1/diff",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FileDiffController {

	private DiffDocumentService diffDocumentService;

	@Autowired
	public FileDiffController(DiffDocumentService storeRateService) {
		this.diffDocumentService = storeRateService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public DiffDocumentResponse compare(
			@PathVariable long id)  {
		
		DiffDocumentResponse diffDocumentResponse = new DiffDocumentResponse();
		diffDocumentResponse.setStatus("OK");
		diffDocumentResponse.setEqual(diffDocumentService.compare(id));
		return diffDocumentResponse;
	}

	@PutMapping(path = "/{id}/left")
	public ServiceResponse storeLeft(
			@PathVariable long id,
			@RequestBody String contentBase64)
			 {

		if (contentBase64 == null) {
			return ServiceResponse.buildError(new BadRequestException());
		}
		
		byte[] imageByte=Base64.getDecoder().decode(contentBase64);
		
		Document document = new Document();
		document.setOperantionId(id);
		document.setContentBase64(imageByte);
		
		diffDocumentService.left(document);

		return ServiceResponse.buildOK();
	}
	
	@PutMapping(path = "/{id}/right")
	public ServiceResponse storeRight(
			@PathVariable long id,
			@RequestBody String contentBase64)
			{

		if (contentBase64 == null) {
			return ServiceResponse.buildError(new BadRequestException());
		}
		
		
		byte[] imageByte=Base64.getDecoder().decode(contentBase64);
		
		Document document = new Document();
		document.setOperantionId(id);
		document.setContentBase64(imageByte);
		
		diffDocumentService.right(document);

		return ServiceResponse.buildOK();
	}
}
