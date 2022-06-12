package com.learning.season.service;

import com.learning.season.dto.CommonResponseModel;
import com.learning.season.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {

    CommonResponseModel createUserInfo(UserInfoDto userInfoDto);

    List<UserInfoDto> getAllInfo();

    UserInfoDto getUserInfoById(int id);

    UserInfoDto updateUserInfo(UserInfoDto userInfoDto, int id);

    void deleteUserInfoById(int id);
}
