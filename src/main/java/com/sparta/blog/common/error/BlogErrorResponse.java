package com.sparta.blog.common.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

@Getter
@Builder
public class BlogErrorResponse {
    private final int errorCode;
    private final String errorMessage;

    public static ResponseEntity<BlogErrorResponse> toResponseEntity(BlogErrorCode error) {
        return ResponseEntity
                .status(error.getErrorCode())
                .body(BlogErrorResponse.builder()
                        .errorCode(error.getErrorCode())
                        .errorMessage(error.getErrorMessage())
                        .build()
                );

    }
}
