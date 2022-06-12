package com.learning.season.service.serviceimpl;

import com.learning.season.service.TestService;
import org.springframework.stereotype.Service;


@Service
public class TestServiceImpl implements TestService {


    @Override
    public String callingText() {
        return "Hello, User";
    }

    @Override
    public int callingInteger() {
        return 377;
    }

    @Override
    public String[] callingArray() {

        return new String[] {
                "Japan",
                "India",
                "Austria",
                "Dubai"
        };
    }


}
