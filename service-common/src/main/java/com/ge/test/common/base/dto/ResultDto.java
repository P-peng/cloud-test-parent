package com.ge.test.common.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author dengzhipeng
 * @version 1.0
 * @date 2020/5/25 0025
 */
@Getter
@Setter
public class ResultDto<T> implements Serializable {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    public ResultDto(){}

    public ResultDto(T data){
        this.code = 200;
        this.data = data;
    }

    public ResultDto(Integer code, T data){
        this.code = code;
        this.data = data;
    }

    public ResultDto(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultDto ok(T data){
        ResultDto resultDto = new ResultDto(data);
        return resultDto;
    }

}
