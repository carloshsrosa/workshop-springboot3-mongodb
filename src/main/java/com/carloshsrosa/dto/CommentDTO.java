package com.carloshsrosa.dto;

import java.io.Serializable;
import java.time.Instant;

public class CommentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String text;
	private Instant date;

	private AuthorDTO autor;
	
	public CommentDTO() {
	}

	public CommentDTO(String text, Instant date, AuthorDTO autor) {
		this.text = text;
		this.date = date;
		this.autor = autor;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public AuthorDTO getAutor() {
		return autor;
	}

	public void setAutor(AuthorDTO autor) {
		this.autor = autor;
	}
}
