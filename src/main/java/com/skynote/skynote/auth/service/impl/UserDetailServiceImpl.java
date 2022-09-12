package com.skynote.skynote.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.skynote.skynote.auth.dao.repository.UserRepository;
import com.skynote.skynote.auth.dao.entity.User;
import com.skynote.skynote.auth.dao.entity.SecurityUser;
import com.skynote.skynote.auth.dto.SecurityUserDto;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }    

    @Override
    public UserDetails loadUserByUsername(String phoneNum) throws UsernameNotFoundException {

        User user = userRepository.findByPhoneNum(phoneNum)
        .orElseThrow(()->new UsernameNotFoundException(phoneNum + ": 사용자가 존재하지 않습니다."));

        return new SecurityUser(SecurityUserDto.from(user));
    }
}
