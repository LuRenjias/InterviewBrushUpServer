package edu.hnu.utils;

import lombok.Getter;

@Getter
public enum StatusCode {
    // openid获取异常
    OPENID_ERROR(500),

    // 上传的头像文件为空
    FILE_IS_EMPTY(501),

    // 上传的头像不是图片格式
    ILLEGAL_FORMAT(502),

    // 传入 id 数量为 0
    NO_SELECT_ID(600),

    // 管理员登录异常，数据库没有对应信息或密码有无
    LOGIN_ERROR(601),

    // 数据库访问数据异常
    DATABASE_ERROR(602),

    // 传入数据类型有误
    DATA_TYPE_ERROR(603),

    // 传入 id 不存在
    MISSING_SELECT_ID(604);

    // 状态码
    private final int code;

    // 构造函数
    StatusCode(int code) {
        this.code = code;
    }

}
