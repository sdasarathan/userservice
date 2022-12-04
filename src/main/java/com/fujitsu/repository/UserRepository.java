package com.fujitsu.repository;

import com.fujitsu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public boolean existsUserByEmailAddress(String emailAddress);

}
