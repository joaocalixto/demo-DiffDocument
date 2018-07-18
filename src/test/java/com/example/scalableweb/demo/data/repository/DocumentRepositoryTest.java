package com.example.scalableweb.demo.data.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.scalableweb.demo.DefaultEntityUtils;
import com.example.scalableweb.demo.data.entity.Document;
import com.example.scalableweb.demo.data.repo.DocumentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DocumentRepositoryTest {
	
	
	@Autowired
	private DocumentRepository documentRepository;

	@Test
	@DirtiesContext
	public void findByDateBetweenOrderByDateAsc() {
		//given
		Document sampleLeftDoc = new DefaultEntityUtils().sampleDocumentA();
		Document sampleRightDoc = new DefaultEntityUtils().sampleDocumentA();

		documentRepository.saveAndFlush(sampleLeftDoc);
		documentRepository.saveAndFlush(sampleRightDoc);

		//when
		List<Document> findByOperantionId = documentRepository.findByOperantionId(sampleLeftDoc.getOperantionId());

		//then
		assertThat(findByOperantionId, notNullValue());
		assertThat(findByOperantionId, hasSize(2));
		assertThat(findByOperantionId.get(0).getOperantionId(), equalTo(sampleLeftDoc.getOperantionId()));
		assertThat(findByOperantionId.get(1).getOperantionId(), equalTo(sampleRightDoc.getOperantionId()));
	}

}
