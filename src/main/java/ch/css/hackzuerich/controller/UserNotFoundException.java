package ch.css.hackzuerich.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zberg.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException {
    // nothing to do :)
}
