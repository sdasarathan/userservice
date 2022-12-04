package com.fujitsu.service;

import com.fujitsu.config.BlockListConfig;
import com.fujitsu.exception.InvalidEmailExceptions;
import com.fujitsu.model.User;
import com.fujitsu.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockListConfig blocklist;

    public User createUser(User user) throws InvalidEmailExceptions {
        LOGGER.debug("Blocklist size"+blocklist.getBlockList().size());

        if(!EmailValidator.getInstance().isValid(user.getEmailAddress())){
            throw new InvalidEmailExceptions("Invalid email address:"+user.getEmailAddress());
        }

        String domain = user.getEmailAddress().substring(user.getEmailAddress().indexOf("@") + 1);
        if(blocklist.getBlockList().contains(domain)){
           throw new InvalidEmailExceptions("Email Address is in block list. Cannot create user for this email address:"+user.getEmailAddress());
        }

        if(isEmailAlreadyExist(user)){
            throw new InvalidEmailExceptions("User already exist. Cannot create user for this email address:"+user.getEmailAddress());
        }else{
            return userRepository.save(user);
        }

    }

    public User getUser(User user){
        return userRepository.findById(user.getId()).get();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public String deleteUser(User user){
        if(userRepository.existsById(user.getId())){
            userRepository.delete(user);
            return "User deleted";
        }else{
            return "User do not exist, nothing to delete";
        }
    }

    public boolean isEmailAlreadyExist(User user){
        return userRepository.existsUserByEmailAddress(user.getEmailAddress());
    }
}
