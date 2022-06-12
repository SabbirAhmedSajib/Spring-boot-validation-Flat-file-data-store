package com.learning.season.service.serviceimpl;

import com.learning.season.dto.CommonResponseModel;
import com.learning.season.dto.UserInfoDto;
import com.learning.season.entity.UserInfoEntity;
import com.learning.season.exception.ResourceNotFoundException;
import com.learning.season.repository.UserInfoRepo;
import com.learning.season.service.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private ModelMapper mapper;

    private UserInfoRepo userInfoRepo;

    public UserInfoServiceImpl(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    @Override
    public CommonResponseModel createUserInfo(UserInfoDto userInfoDto) {

        CommonResponseModel commonResponseModel = new CommonResponseModel();

        UserInfoEntity userInfoEntity = userInfoRepo.save(mapper.map(userInfoDto, UserInfoEntity.class));
        if ((userInfoEntity.getUserId()) > 0){
            commonResponseModel.setResponseCode(1);
            commonResponseModel.setResponseMessage("User Info Save Successfully");
            commonResponseModel.setId(userInfoEntity.getUserId());
        }

        return commonResponseModel;

    }

    @Override
    public List<UserInfoDto> getAllInfo() {
        List<UserInfoEntity> userInfoEntities= userInfoRepo.findAll();
        return userInfoEntities.stream().map
                (userInfoEntity -> mapToDTO(userInfoEntity)).collect(Collectors.toList());

    }

    @Override
    public UserInfoDto getUserInfoById(int id) {
        UserInfoEntity userInfoEntity=userInfoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserInfoEntity","id",id));
        return mapToDTO(userInfoEntity);
    }

    @Override
    public UserInfoDto updateUserInfo(UserInfoDto userInfoDto, int id) {
        //get UserInfo by id from the database
        UserInfoEntity userInfoEntity=userInfoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserInfoEntity","id",id));
        userInfoEntity.setName(userInfoDto.getName());
        userInfoEntity.setEmail(userInfoDto.getEmail());
        userInfoEntity.setAddress(userInfoDto.getAddress());
        userInfoEntity.setPhoneNumber(userInfoDto.getPhoneNumber());
        userInfoEntity.setGender(userInfoDto.getGender());
        userInfoEntity.setUserDOB(userInfoDto.getUserDOB());

        UserInfoEntity updateUserInfos=userInfoRepo.save(userInfoEntity);
        return mapToDTO(updateUserInfos);
    }


    @Override
    public void deleteUserInfoById(int id) {
        // get User Info by id from the database
        UserInfoEntity userInfoEntity = userInfoRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("UserInfoEntity", "id", id));
        userInfoRepo.delete(userInfoEntity);
    }


    //Convert Entity into DTO
    private UserInfoDto mapToDTO(UserInfoEntity userInfoEntity){
        UserInfoDto userInfoDto= new UserInfoDto();

        userInfoDto.setUserId(userInfoEntity.getUserId());
        userInfoDto.setName(userInfoEntity.getName());
        userInfoDto.setAddress(userInfoEntity.getAddress());
        userInfoDto.setEmail(userInfoEntity.getEmail());
        userInfoDto.setGender(userInfoEntity.getGender());
        userInfoDto.setUserDOB(userInfoEntity.getUserDOB());
        userInfoDto.setPhoneNumber(userInfoEntity.getPhoneNumber());
        return userInfoDto;
    }

    //Converted DTO to Entity
    private UserInfoEntity mapToEntity(UserInfoDto userInfoDto){
        UserInfoEntity userInfoEntity=new UserInfoEntity();
        userInfoEntity.setUserId(userInfoDto.getUserId());
        userInfoEntity.setName(userInfoDto.getName());
        userInfoEntity.setAddress(userInfoDto.getAddress());
        userInfoEntity.setEmail(userInfoDto.getEmail());
        userInfoEntity.setGender(userInfoDto.getGender());
        userInfoEntity.setUserDOB(userInfoDto.getUserDOB());
        userInfoEntity.setPhoneNumber(userInfoDto.getPhoneNumber());
        return userInfoEntity;
    }
}
