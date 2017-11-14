package com.dqr.messagerelay.dto;

import lombok.Data;

@Data
public class UserInfo {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Boolean active;
}
