package com.fujitsu.service;

import com.fujitsu.exception.InvalidEmailExceptions;
import com.fujitsu.model.Gender;
import com.fujitsu.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    private UserService userService;


    @Test
    public void createUserValidEmailAddress() throws Exception {
        String emailAddress = "username@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setBirthdate(new Date());
        user.setGender(Gender.MALE);
        user.setPassword("password");
        user.setNickName("nickname");
        user.setEmailAddress(emailAddress);
        userService.createUser(user);
    }

    @Test
    public void createUserInBlockList() throws Exception {
        exceptionRule.expect(InvalidEmailExceptions.class);
        exceptionRule.expectMessage("Email Address is in block list. Cannot create user for this email address");
        String emailAddress = "username@30minutemail.com";
        User user = new User();
        user.setId(1L);
        user.setBirthdate(new Date());
        user.setGender(Gender.MALE);
        user.setPassword("password");
        user.setNickName("nickname");
        user.setEmailAddress(emailAddress);
        userService.createUser(user);
    }

    @Test()
    public void createUserWithInvalidEmail() throws Exception {
        exceptionRule.expect(InvalidEmailExceptions.class);
        exceptionRule.expectMessage("Invalid email address");
        String emailAddress = "username";
        User user = new User();
        user.setId(1L);
        user.setBirthdate(new Date());
        user.setGender(Gender.MALE);
        user.setPassword("password");
        user.setNickName("nickname");
        user.setEmailAddress(emailAddress);
        userService.createUser(user);
    }

    @Test()
    public void createUserWithExistingEmail() throws Exception {
        exceptionRule.expect(InvalidEmailExceptions.class);
        exceptionRule.expectMessage("User already exist. Cannot create user for this email address:username1@gmail.com");
        String emailAddress = "username1@gmail.com";
        User user = new User();
        user.setId(1L);
        user.setBirthdate(new Date());
        user.setGender(Gender.MALE);
        user.setPassword("password");
        user.setNickName("nickname");
        user.setEmailAddress(emailAddress);
        userService.createUser(user);

        User user2 = new User();
        user2.setId(2L);
        user2.setBirthdate(new Date());
        user2.setGender(Gender.MALE);
        user2.setPassword("password");
        user2.setNickName("nickname");
        user2.setEmailAddress(emailAddress);
        userService.createUser(user2);
    }

}