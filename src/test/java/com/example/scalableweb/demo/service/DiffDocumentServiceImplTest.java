package com.example.scalableweb.demo.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.scalableweb.demo.DefaultEntityUtils;
import com.example.scalableweb.demo.data.entity.Document;

public class DiffDocumentServiceImplTest {
	
	@Mock
	private StoreDocumentService storeDocumentService;

	DiffDocumentServiceImpl diffDocumentService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.diffDocumentService = new DiffDocumentServiceImpl(this.storeDocumentService);
	}
	
	@Test
	public void campareEqualDocumentsOK() {
		//given
		DefaultEntityUtils defaultEntityUtils = new DefaultEntityUtils();
		List<Document> sampleListEqualDocuments = defaultEntityUtils.sampleListEqualDocuments();
		Document sampleDocumentA = defaultEntityUtils.sampleDocumentA();
		when(storeDocumentService.findByOperantionId(sampleDocumentA.getOperantionId())).thenReturn(sampleListEqualDocuments);

		//when
		boolean compare = diffDocumentService.compare(sampleDocumentA.getOperantionId());

		//then
		assertTrue(compare);
		verify(storeDocumentService, Mockito.times(1)).findByOperantionId(sampleDocumentA.getOperantionId());
		verifyNoMoreInteractions(storeDocumentService);
	}
	
	@Test
	public void campareDiferentDocumentsOK() {
		// given
		DefaultEntityUtils defaultEntityUtils = new DefaultEntityUtils();
		List<Document> sampleListEqualDocuments = defaultEntityUtils.sampleListDifferentDocuments();
		Document sampleDocumentA = defaultEntityUtils.sampleDocumentA();
		when(storeDocumentService.findByOperantionId(sampleDocumentA.getOperantionId()))
				.thenReturn(sampleListEqualDocuments);

		// when
		boolean compare = diffDocumentService.compare(sampleDocumentA.getOperantionId());

		// then
		assertTrue(!compare);
		verify(storeDocumentService, Mockito.times(1)).findByOperantionId(sampleDocumentA.getOperantionId());
		verifyNoMoreInteractions(storeDocumentService);
	}

}
