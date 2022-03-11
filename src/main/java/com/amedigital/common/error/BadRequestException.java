package com.amedigital.common.error;

public class BadRequestException extends RuntimeException {
  private int code;

  public BadRequestException(String message, int code) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
}
