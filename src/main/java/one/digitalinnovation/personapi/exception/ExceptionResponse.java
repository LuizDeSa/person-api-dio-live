package one.digitalinnovation.personapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private final String message;
    private List<String> errors;


    public ExceptionResponse(HttpStatus httpStatus, String message, String error){
        this.httpStatus = httpStatus;
        this.message = message;
        this.errors = Arrays.asList(error);
    }
}
