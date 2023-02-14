package com.websocket.RadioChanel.repository;

import com.websocket.RadioChanel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {

    }

