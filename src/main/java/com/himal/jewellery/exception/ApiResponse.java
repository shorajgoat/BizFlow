package com.himal.jewellery.exception;

import java.time.LocalDateTime;

public class ApiResponse <T>{
private LocalDateTime timeStamp;
private int status;
private String message;
private T data;
public ApiResponse( int status, String message, T data) {
	this.timeStamp = LocalDateTime.now();
	this.status = status;
	this.message = message;
	this.data = data;
}
public LocalDateTime getTimeStamp() {
	return timeStamp;
}
public int getStatus() {
	return status;
}
public String getMessage() {
	return message;
}
public T getData() {
	return data;
}



}
