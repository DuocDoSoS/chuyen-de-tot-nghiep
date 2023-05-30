package com.example.silver.util;

import com.example.silver.model.response.ResponseModel;
import com.example.silver.model.response.ResultModel;

public class ResponseUtils {
    public static ResponseModel responseFail(String message){
        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(false);
        responseModel.setResult(new ResultModel<>(null,message));
        return responseModel;
    }
}
