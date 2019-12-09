package com.sut62.team07.request;

import java.util.Collection;

import lombok.Data;

@Data
public class LecturerRequest {

    private String lecturerCode;

    private String password;

    private String personalId;

    private String name;

    private String email;

    private String tel;

    private Long major;

    private Long gender;

    private Collection<Long> courses;
}