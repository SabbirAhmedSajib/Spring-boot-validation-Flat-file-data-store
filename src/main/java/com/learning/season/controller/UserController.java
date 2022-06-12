package com.learning.season.controller;

import com.learning.season.dto.CommonResponseModel;
import com.learning.season.dto.UserInfoDto;
import com.learning.season.procedure.UserSetup;
import com.learning.season.service.FileReader;
import com.learning.season.service.TestService;
import com.learning.season.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserSetup userSetup;

    private final TestService testService;

    @Autowired
    private FileReader fileReader;

    @Autowired
    private UserInfoService userInfoService;

    public UserController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/Integer")
    public int firstService(){
        return testService.callingInteger();
    }

    @GetMapping("/Text")
    public String secondService(){
        return testService.callingText();
    }

    @GetMapping("/Array")
    public String[] thirdService(){
        return testService.callingArray();
    }


    @PostMapping("/Create")
    public CommonResponseModel createPost(@RequestBody UserInfoDto userInfoDto){
        return userInfoService.createUserInfo(userInfoDto);

    }

    @GetMapping("/GetInfos")
    public List<UserInfoDto> getUserInfos(){
        return userInfoService.getAllInfo();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getPostById(@PathVariable(name = "id") int id){
        return ResponseEntity.ok(userInfoService.getUserInfoById(id));

    }

    //update UserInfo by id rest api
    @PutMapping("/{id}")
    public  ResponseEntity<UserInfoDto> updatePost(@RequestBody UserInfoDto userInfoDto, @PathVariable(name = "id") int id ){
        UserInfoDto postResponse = userInfoService.updateUserInfo(userInfoDto,id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable(name = "id") int id){

        userInfoService.deleteUserInfoById(id);
    }

    @PostMapping("/file")
    public void file(@RequestBody UserInfoDto userInfoDto) {

         fileReader.fileRead(userInfoDto);
    }

    @GetMapping("/read")
    public void fileRead() throws IOException {

        fileReader.textFileRead();
    }

    @PostMapping("/readwrite")
    public void readwrite(@RequestBody UserInfoDto userInfoDto) throws IOException {

        fileReader.fileReadWrite(userInfoDto);
    }



    @PostMapping("/userSetup")
    public Map<String, Object> UserMasterCreation(@RequestBody UserInfoDto userInfoDto) {
        Map<String, Object> map = new HashMap<>();
        UserInfoDto returnObj = userSetup.dprInsertUserInfo(userInfoDto);
        map.put("sourceFlag", returnObj.getSourceFlag());
        map.put("sourceData", returnObj.getSourceData());
        return map;
    }




}
