package com.sut62.team07.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LecturerRequest {

    @NotNull
    private String lecturerCode;

    @NotNull
    private String password;

    @NotNull
    private String personalId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String tel;

    @NotNull
    private Long prefix;

    @NotNull
    private Long major;

    @NotNull
    private Long gender;

    @NotNull
    private String createdBy;
}