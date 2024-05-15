package edu.hnu.utils;

public enum StatusCode {
    // openid获取异常
    OPENID_ERROR(500),

    // 上传的头像文件为空
    FILE_IS_EMPTY(501);

    // 状态码
    private final int code;

    // 构造函数
    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
