package com.skynote.skynote.auth.dao.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.skynote.skynote.auth.dto.SecurityUserDto;

public class SecurityUser extends User {
    
    private static final long serialVersionUiD = 1L;

    public SecurityUser(SecurityUserDto SecurityUserDto){
        super(SecurityUserDto.getPhoneNum(),"{noop}"+ SecurityUserDto.getPassword(), 
        AuthorityUtils.createAuthorityList(SecurityUserDto.getRole().toString()));
    }

}
