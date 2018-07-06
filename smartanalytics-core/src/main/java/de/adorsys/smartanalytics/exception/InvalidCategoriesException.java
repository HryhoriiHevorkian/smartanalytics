package de.adorsys.smartanalytics.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "INVALID_CATEGORIES"
)
public class InvalidCategoriesException extends ParametrizedMessageException {

    public InvalidCategoriesException(String message) {
        super("unable import categories");
        this.addParam("message", message);
    }

}
