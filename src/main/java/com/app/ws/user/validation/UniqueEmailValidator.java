package com.app.ws.user.validation;

import com.app.ws.user.User;
import com.app.ws.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        User inDB = userRepository.findByEmail(s);
        if (inDB != null) {
            return false;
        }
        return true;

    }
}
