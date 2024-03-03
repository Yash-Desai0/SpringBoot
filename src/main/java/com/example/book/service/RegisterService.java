package com.example.book.service;

import com.example.book.Repository.RegisterRepository;
import com.example.book.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    public void save(User user)
    {
         registerRepository.save(user);
    }

}
