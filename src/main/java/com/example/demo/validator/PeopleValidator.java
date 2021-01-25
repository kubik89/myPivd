package com.example.demo.validator;

import com.example.demo.dto.PeopleCreateDto;
import com.example.demo.entity.People;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeopleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(People.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PeopleCreateDto people = (PeopleCreateDto) o;
        if (people.getLName().length() < 3) {
            throw new IllegalArgumentException("First name must have min 3 letters");
        }
    }
}
