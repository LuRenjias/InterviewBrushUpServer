package edu.hnu.utils;

import lombok.Getter;

@Getter
public enum StatusCode {
    // openid获取异常
    OPENID_ERROR(500),

    // 上传的头像文件为空
    FILE_IS_EMPTY(501),

    // 上传的头像不是图片格式
    ILLEGAL_FORMAT(502);

    // 状态码
    private final int code;

    // 构造函数
    StatusCode(int code) {
        this.code = code;
    }

}
