package elisadari.UN5W3d1praticaS3L1.exceptions;

import elisadari.UN5W3d1praticaS3L1.payloads.ErrorsPayloadDTO;
import elisadari.UN5W3d1praticaS3L1.payloads.ListofErrorsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExHandler {
    @ExceptionHandler(NotFoundEx.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayloadDTO handle404(NotFoundEx ex) {
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestEx.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ListofErrorsDTO handleB_R(BadRequestEx ex) {
        List<String> errorsMsgs = new ArrayList<>();
        if (ex.getErrors() != null)
            errorsMsgs = ex.getErrors().stream().map((error -> error.getDefaultMessage())).toList();
        return new ListofErrorsDTO(ex.getMessage(), LocalDateTime.now(), errorsMsgs);

    }
    @ExceptionHandler(UnauthorizedEx.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsPayloadDTO handle401(UnauthorizedEx ex){
        return new ErrorsPayloadDTO(ex.getMessage(),LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayloadDTO handle500(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayloadDTO("ERRORE LATO SERVER-working on it",LocalDateTime.now());
    }

}
