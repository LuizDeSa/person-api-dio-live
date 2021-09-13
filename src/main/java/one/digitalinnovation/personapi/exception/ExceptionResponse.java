package one.digitalinnovation.personapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private final String message;
    private List<String> errors;

    public ExceptionResponse(String message, String error){
        this.message = message;
        this.errors = Arrays.asList(error);
    }
}
