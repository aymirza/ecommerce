package com.example.ecommerce.exception;

import com.example.ecommerce.enums.ResultEnum;

public class MyException extends RuntimeException{
    private Integer code;

    public MyException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public MyException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
