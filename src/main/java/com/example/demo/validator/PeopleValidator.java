package com.example.demo.validator;

import com.example.demo.dto.PeopleCreateDto;
import com.example.demo.entity.People;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeopleValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(PeopleCreateDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PeopleCreateDto people = (PeopleCreateDto) o;
        System.out.println("Last name = " + people.getLname());
        if(people.getLname().equals("")) {
        System.out.println("Validator... Last name is empty");
            errors.rejectValue("lname", "is empty", "can not be empty");
        }
        if (people.getLname().length() < 3) {
//            throw new IllegalArgumentException("First name must have min 3 letters");
            errors.rejectValue("lname", "minimal 3 letters", "should start with 3 letters");
        }
    }
}
