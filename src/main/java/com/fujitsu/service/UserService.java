package com.fujitsu.service;

import com.fujitsu.model.User;
import com.fujitsu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUser(User user){
        return userRepository.findById(user.getId()).get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String deleteUser(User user){
        userRepository.delete(user);
        return "User deleted";
    }

    public boolean isEmailAlreadyExist(User user){
        userRepository.findByEmailAddress(user.getEmailAddress()).
    }
}
