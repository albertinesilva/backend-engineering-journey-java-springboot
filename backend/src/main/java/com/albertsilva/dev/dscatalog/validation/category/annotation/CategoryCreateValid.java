package com.albertsilva.dev.dscatalog.validation.category.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

import com.albertsilva.dev.dscatalog.validation.category.validator.CategoryCreateValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = CategoryCreateValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryCreateValid {

  String message() default "Validation error";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}