package com.matthewlooman.retriever.rest.exception;

public class DuplicateDeviceNameException extends RuntimeException {
  public DuplicateDeviceNameException(String message){
    super(message);
  }
}
