package com.app.ws.user;

import com.app.ws.error.ApiError;
import com.app.ws.shared.GenericMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user) {
        userService.save(user);
        return new GenericMessage("User is created");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)

    ResponseEntity<ApiError> handelMethodArgNotValidException(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setStatus(400);
        apiError.setMessage("Validation error");
        apiError.setPath("/api/v1/users");
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError fieldErrors: exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldErrors.getField(), fieldErrors.getDefaultMessage());
        }
        //Map<String, String> validationErrors = exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }
}
