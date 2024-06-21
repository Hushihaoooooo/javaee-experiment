package com.shihaohu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shihaohu
 * @date 2024/6/21 11:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;
    //静态函数可以避免每次使用都创建对象，不容易出错
    //带数据返回
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }
    //简易返回
    public static Result success() {
        return new Result<>(0, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result<>(1, message, null);
    }
}
