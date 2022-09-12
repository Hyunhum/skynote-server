package com.skynote.skynote.auth.service;

import com.skynote.skynote.auth.dto.*;

public interface AuthService {

    ResponseDto signUpUser(SignUpUserDto userDto) throws Exception;

    LogInUserDto logInUser(LogInUserDto userDto) throws Exception;

    ResponseDto changePasswordUser(ChangePasswordUserDto userDto) throws Exception;

}