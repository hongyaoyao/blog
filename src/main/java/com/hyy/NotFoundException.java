package com.hyy;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @PROJECT_NAME: blog
 * @PACKAGE_NAME: com.hyy
 * @CLASS_NAME: NotFoundException
 * @USER: hongyaoyao
 * @DATETIME: 2023/6/11 17:07
 * @Emial: 1299694047@qq.com
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
