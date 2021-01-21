package com.example.task2.service;

import com.example.task2.dto.PersonActivityDto;

import java.util.ArrayList;
import java.util.List;

public class PersonActivityServiceStub {

    private final List<PersonActivityDto> personActivityDtoList;
    private static PersonActivityServiceStub INSTANCE;

    public static void initialize() {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = new PersonActivityServiceStub();
    }

    public static PersonActivityServiceStub getInstance() {
        return INSTANCE;
    }

    public void addUserActivity(PersonActivityDto personActivityDto) {
        personActivityDtoList.add(personActivityDto);
    }

    public List<PersonActivityDto> getPersonActivities() {
        return personActivityDtoList;
    }

    private PersonActivityServiceStub() {
        personActivityDtoList = new ArrayList<>();
    }
}
