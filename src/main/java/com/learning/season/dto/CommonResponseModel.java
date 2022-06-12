package com.learning.season.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponseModel {
    private long id;
    private int responseCode;
    private String responseMessage;
}
