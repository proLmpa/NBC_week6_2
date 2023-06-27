package com.sparta.blog.common.exception;

import com.sparta.blog.common.error.BlogError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST) // response로 들어가는 에러 코드를 400으로 통일
public class BlogException extends RuntimeException {
    private final BlogError error;

    public BlogException(BlogError error, Throwable cause) {
        super(error.getErrorMessage(), cause, false, false);
        this.error = error;
    }

    public BlogError getErrorCode() {
        return this.error;
    }
}
