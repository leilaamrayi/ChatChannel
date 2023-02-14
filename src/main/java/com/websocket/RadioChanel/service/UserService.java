package com.websocket.RadioChanel.service;

import com.websocket.RadioChanel.model.User;
import com.websocket.RadioChanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


    @Service
    public class UserService {

        @Autowired
        private UserRepository userRepository;

        public List<User> getAll() {
            return userRepository.findAll();
        }

        public User get(long id) {
            return userRepository.getReferenceById(id);
        }

        public User save(User user) {
            return userRepository.save(user);
        }

        public void delete(long userId) {
            userRepository.deleteById(userId);
        }
}
