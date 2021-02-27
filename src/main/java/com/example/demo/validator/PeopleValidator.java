package com.example.demo.validator;

import com.example.demo.dto.PeopleViewCurrentUserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeopleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PeopleViewCurrentUserDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
//        PeopleViewCurrentUserDto people = (PeopleViewCurrentUserDto) o;
//        if (people.getLname().length() < 3) {
//            throw new IllegalArgumentException("First name must have min 3 letters");
//        }
    }
}
