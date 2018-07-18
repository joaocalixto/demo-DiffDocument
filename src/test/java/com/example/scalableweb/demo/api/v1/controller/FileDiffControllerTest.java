package com.example.scalableweb.demo.api.v1.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.scalableweb.demo.DefaultEntityUtils;
import com.example.scalableweb.demo.IntegrationTestUtil;
import com.example.scalableweb.demo.api.v1.controller.model.ServiceResponse;
import com.example.scalableweb.demo.data.entity.Document;
import com.example.scalableweb.demo.service.DiffDocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileDiffControllerTest {
	
	public static final String URL_TEMPLATE = "/api/v1/diff/";
	public static final String URL_TEMPLATE_RANGE = URL_TEMPLATE + "range";

	private FileDiffController fileDiffController;

	@Mock
	private DiffDocumentService diffDocumentService;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.fileDiffController = new FileDiffController(this.diffDocumentService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.fileDiffController).build();
	}


	@Test
	public void insertLeftDocumentOK() throws Exception {
		//given
		Document sampleDocumentA = new DefaultEntityUtils().sampleDocumentA();
		ServiceResponse sampleServiceResponseOK = ServiceResponse.buildOK();
		final String rateRetJson = new ObjectMapper().writeValueAsString(sampleServiceResponseOK);
		
		//when
		final ResultActions perform = mockMvc.perform(
				put(URL_TEMPLATE +"{id}/left", sampleDocumentA.getOperantionId())
				.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
				.content(Base64.encodeBase64String(sampleDocumentA.getContentBase64())));
                

		//then
		perform.andExpect(status().isOk());
		perform.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		perform.andExpect(content().json(rateRetJson));

	}
	
	@Test
	public void insertLeftDocumentEmpty() throws Exception {
		//given
		Document sampleDocumentA = new DefaultEntityUtils().sampleEmptyDocument();
		
		//when
		final ResultActions perform = mockMvc.perform(
				put(URL_TEMPLATE +"{id}/left", sampleDocumentA.getOperantionId())
				.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
				.content(Base64.encodeBase64String(sampleDocumentA.getContentBase64())));
                

		//then
		perform.andExpect(status().isBadRequest());
	}
	
	@Test
	public void compareDocumentsOK() throws Exception {
		// given
		Document sampleDocumentA = new DefaultEntityUtils().sampleDocumentA();
		ServiceResponse sampleServiceResponseOK = ServiceResponse.buildOK();
		final String rateRetJson = new ObjectMapper().writeValueAsString(sampleServiceResponseOK);
		when(this.diffDocumentService.compare(sampleDocumentA.getOperantionId())).thenReturn(true);
		
		ServiceResponse sampleServiceCompareOK = DefaultEntityUtils.sampleServiceCompareOK();
		final String sampleServiceCompareOKJson = new ObjectMapper().writeValueAsString(sampleServiceCompareOK);


		final ResultActions perform = mockMvc.perform(put(URL_TEMPLATE + "{id}/left", sampleDocumentA.getOperantionId())
				.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
				.content(Base64.encodeBase64String(sampleDocumentA.getContentBase64())));

		perform.andExpect(status().isOk());
		perform.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		perform.andExpect(content().json(rateRetJson));
		
		final ResultActions performRight = mockMvc.perform(put(URL_TEMPLATE + "{id}/right", sampleDocumentA.getOperantionId())
				.contentType(IntegrationTestUtil.APPLICATION_JSON_UTF8)
				.content(Base64.encodeBase64String(sampleDocumentA.getContentBase64())));

		performRight.andExpect(status().isOk());
		performRight.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		performRight.andExpect(content().json(rateRetJson));
		
		//when
		final ResultActions performCompare = mockMvc.perform(get(URL_TEMPLATE + "{id}", sampleDocumentA.getOperantionId()));

		performCompare.andExpect(status().isOk());
		performCompare.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		performCompare.andExpect(content().json(sampleServiceCompareOKJson));
		
		verify(diffDocumentService, Mockito.times(1)).compare(sampleDocumentA.getOperantionId());

	}

}
