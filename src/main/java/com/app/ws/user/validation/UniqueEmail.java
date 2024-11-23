package com.app.ws.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
        validatedBy = UniqueEmailValidator.class
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) // Target, Constraint ve Retention annotationları yeni bir annotation create ederken create edilen annotation üzerine eklenmelidir.
public @interface UniqueEmail {

    String message() default "E-mail kullanılıyor";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {}; // message, groups ve payload değişkenleri yeni bir annotation create ederken create edilen annotation'ın içerisine eklenmelidir.

}
