package com.quiz.entites;

public class Responses {

	private long id;
	private String response;
	
	
	public Responses() {}
	
	public Responses(long id, String response) {
		this.id = id;
		this.response = response;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}
