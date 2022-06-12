package com.learning.season.procedure;


import com.learning.season.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Optional;

@Service
@Slf4j
public class UserSetup {

    @Autowired
    private EntityManager oEntityManager;


    public UserInfoDto dprInsertUserInfo(UserInfoDto userInfoDto){
        UserInfoDto returnObj = new UserInfoDto();

        StoredProcedureQuery storedProcedureQuery = oEntityManager.createStoredProcedureQuery("locker.dpk_learning_season.dpr_insert_userinfo");


        storedProcedureQuery.registerStoredProcedureParameter(1, int.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(7, String.class, ParameterMode.IN);



        storedProcedureQuery.registerStoredProcedureParameter(8, String.class, ParameterMode.OUT);
        storedProcedureQuery.registerStoredProcedureParameter(9, String.class, ParameterMode.OUT);

        System.out.println("userInfoDto.getUserId(): " + userInfoDto.getUserId());
        System.out.println("userInfoDto.getName(): " + userInfoDto.getName());
        System.out.println("userInfoDto.getEmail(): " + userInfoDto.getEmail());
        System.out.println("userInfoDto.getPhoneNumber(): " + userInfoDto.getPhoneNumber());
        System.out.println("userInfoDto.getAddress(): " + userInfoDto.getAddress());
        System.out.println("userInfoDto.getGender(): " + userInfoDto.getGender());
        System.out.println("userInfoDto.getDateOfBirth(): " + userInfoDto.getDateOfBirth());

        storedProcedureQuery.setParameter(1, userInfoDto.getUserId());
        storedProcedureQuery.setParameter(2, Optional.ofNullable(userInfoDto.getName()).orElse(""));
        storedProcedureQuery.setParameter(3, Optional.ofNullable(userInfoDto.getEmail()).orElse(""));

       storedProcedureQuery.setParameter(4, Optional.ofNullable(userInfoDto.getPhoneNumber()).orElse(""));
        //storedProcedureQuery.setParameter("p_phonenumber",Optional.ofNullable(userInfoDto.getPhoneNumber()).orElse(""));
        storedProcedureQuery.setParameter(5, Optional.ofNullable(userInfoDto.getAddress()).orElse(""));
        storedProcedureQuery.setParameter(6, Optional.ofNullable(userInfoDto.getGender()).orElse(""));
        storedProcedureQuery.setParameter(7, userInfoDto.getDateOfBirth());


        try {
            storedProcedureQuery.execute();


        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println("storedProcedureQueryOut.setSourceData: " + (String) storedProcedureQuery.getOutputParameterValue(8));
        System.out.println("storedProcedureQueryOut.setSourceData: " + (String) storedProcedureQuery.getOutputParameterValue(9));

        returnObj.setSourceFlag((String) storedProcedureQuery.getOutputParameterValue(8));
        returnObj.setSourceData((String) storedProcedureQuery.getOutputParameterValue(9));

        return returnObj;
    }

}
