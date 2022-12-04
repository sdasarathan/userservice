package com.fujitsu.controller;

import com.fujitsu.dto.UserDto;
import com.fujitsu.exception.InvalidEmailExceptions;
import com.fujitsu.model.User;
import com.fujitsu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    @ExceptionHandler({ InvalidEmailExceptions.class})
    public String createUser(@RequestBody UserDto userDto) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        userService.createUser(user);

        return "User successfully created";
    }

    @GetMapping("user")
    public UserDto getUser(@RequestBody UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        User userResult = userService.getUser(user);
        UserDto userDtoResult = new UserDto();
        BeanUtils.copyProperties(userResult, userDtoResult);
        return userDtoResult;
    }

    @GetMapping("users")
    public List<UserDto> getAllUsers() {
        List<User> userList = userService.getAllUsers();

        List<UserDto> userListDto = new ArrayList<UserDto>();
        for(int i =0; i <= userList.size()-1;i++){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userList.get(i), userDto);
            userListDto.add(userDto);
        }
        return userListDto;
    }

    @DeleteMapping("user")
    public String deleteUser(@RequestBody UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return userService.deleteUser(user);
    }

}
