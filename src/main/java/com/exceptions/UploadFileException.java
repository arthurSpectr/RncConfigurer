package com.exceptions;

import com.responses.Response;
import lombok.Getter;

@Getter
public class UploadFileException implements Response {

    private final String message;
    private final boolean status = false;

    public UploadFileException(String message) {
        this.message = message;
    }


}
