package com.football.model;

import org.springframework.http.HttpStatus;

public class ResponseObj {

	public ResponseObj(HttpStatus status, Object body) {
		super();
		this.status = status;
		this.body = body;
	}

	HttpStatus status;
	Object body;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
