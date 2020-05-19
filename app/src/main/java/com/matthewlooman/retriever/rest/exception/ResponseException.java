package com.matthewlooman.retriever.rest.exception;

public class ResponseException extends RuntimeException {
  public ResponseException(String message){
    super(message);
  }
}
