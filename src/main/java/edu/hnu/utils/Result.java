package edu.hnu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Result.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code; // 状态码 1表示成功 0表示失败

    private String message; // 提示信息

    private Object data; // 返回数据

    /**
     * Success result.
     *
     * @return the result
     */
    public static Result success() {
        return new Result(1, "success", null);
    }

    /**
     * Success result.
     *
     * @param data the data
     * @return the result
     */
    public static Result success(Object data) {
        return new Result(1, "success", data);
    }

    /**
     * Error result.
     *
     * @return the result
     */
    public static Result error() {
        return new Result(0, "error", null);
    }

    /**
     * Error result.
     *
     * @param message the message
     * @return the result
     */
    public static Result error(String message) {
        return new Result(0, message, null);
    }

    /**
     * Error result.
     */
    public static Result error(StatusCode code) {
        return new Result(code.getCode(), code.name(), null);
    }

}
