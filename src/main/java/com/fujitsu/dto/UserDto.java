package com.fujitsu.dto;

import com.fujitsu.model.Gender;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private String nickName;
    private String password;
    private String emailAddress;
    private Gender gender;
    private Date birthdate;
}
