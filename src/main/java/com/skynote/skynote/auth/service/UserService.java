package com.skynote.skynote.auth.service;

import com.skynote.skynote.auth.dto.*;

import javax.servlet.http.Cookie;

public interface UserService {

    public UserInfoDto findUserInfoByRefreshToken(String refreshToken) throws Exception;

}
