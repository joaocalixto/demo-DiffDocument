package com.example.scalableweb.demo.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long operantionId;
	
	@Lob
	@Column(length=100000)
	private byte[] contentBase64;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOperantionId() {
		return operantionId;
	}

	public void setOperantionId(Long operantionId) {
		this.operantionId = operantionId;
	}

	public byte[] getContentBase64() {
		return contentBase64;
	}

	public void setContentBase64(byte[] contentBase64) {
		this.contentBase64 = contentBase64;
	}
	
	

}
