package com.learning.season.service;


import com.learning.season.dto.UserInfoDto;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Service
public class FileReader {

   // public void fileRead(UserInfoDto userInfoDto);



    public void fileRead(UserInfoDto userInfoDto) {

        try {


            // Scanner scan = new Scanner(file);

            FileWriter writer = new FileWriter("D:/Learning Season File/Spring Boot/doc/text.txt");
            writer.write("Name : "+String.valueOf(userInfoDto.getName())+", Email address: " +String.valueOf(userInfoDto.getEmail()
                    +", Phone Number: " +String.valueOf(userInfoDto.getPhoneNumber())));

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void textFileRead() throws IOException {

        File file = new File("D:/Learning Season File/Spring Boot/doc/text.txt");
        Scanner scan = new Scanner(file);

        String fileContent = "THIS IS A NEW FILE MADE BY ME : ";
            while (scan.hasNextLine()) {
                fileContent = fileContent.concat(scan.nextLine() + "\n");
            }

        FileWriter writer = new FileWriter("D:/Learning Season File/Spring Boot/doc/Create.txt");
        writer.write(fileContent);
        writer.close();
    }


    public void fileReadWrite(UserInfoDto userInfoDto) throws IOException {

        File file = new File("D:/Learning Season File/Spring Boot/doc/Creativity.txt");
        Scanner scan = new Scanner(file);
        String fileContent = "\n"+"User ID : "+String.valueOf(userInfoDto.getUserId())+", Name : "+String.valueOf(userInfoDto.getName())+", Email address: " +String.valueOf(userInfoDto.getEmail()
                +", Phone Number: " +String.valueOf(userInfoDto.getPhoneNumber())+", Date of Birth : "+String.valueOf(userInfoDto.getUserDOB()));

        while (scan.hasNextLine()) {
           // fileContent = fileContent.concat(scan.nextLine());
            fileContent = scan.nextLine().concat(fileContent);
        }

        FileWriter writer = new FileWriter("D:/Learning Season File/Spring Boot/doc/Creativity.txt");
        writer.write(fileContent);
        writer.close();
    }

}
