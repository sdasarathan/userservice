package com.fujitsu.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String nickName;

    @Column
    private String password;

    @Column
    private String emailAddress;

    @Column
    private Gender gender;

    @Column
    private Date birthdate;

}
