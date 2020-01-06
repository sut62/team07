package com.sut62.team07.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AuthRequest {

    @NotNull
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;
}