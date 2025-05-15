package az.turing.tinderapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<GlobalResponse> NotFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.ok(GlobalResponse.builder()
                .error("Not found exception")
                .message(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());
    }
    @ExceptionHandler({ArgumentException.class})
    public ResponseEntity<GlobalResponse> ArgumentExceptionHandler(ArgumentException e){
        return ResponseEntity.ok(GlobalResponse.builder()
                .error("Argument exception")
                .message(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .build());
    }
    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<GlobalResponse> UserAlreadyExistException(UserAlreadyExistException e){
        return ResponseEntity.ok(GlobalResponse.builder()
        .error("User already exist exception")
        .message(e.getMessage())
        .timeStamp(LocalDateTime.now())
        .build());
    }
}
