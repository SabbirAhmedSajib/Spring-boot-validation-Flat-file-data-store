package com.learning.season.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class UserInfoDto {
    private int userId;
    private String name;
    private String address;
    private String gender;
    private String email;
    private String phoneNumber;
    private Date userDOB;


    private String dateOfBirth;
    private String sourceData;
    private String sourceFlag;
}
