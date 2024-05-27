package edu.hnu.utils;

import lombok.Getter;

@Getter
public enum StatusCode {
    // 审核中
    UNDER_REVIEW(0),

    // 审核通过
    APPROVED(1),

    // 审核未通过
    REVIEW_FAILED(2),

    // openid获取异常
    OPENID_ERROR(500),

    // 上传的头像文件为空
    FILE_IS_EMPTY(501),

    // 上传的头像不是图片格式
    ILLEGAL_FORMAT(502),

    // 用户不存在
    USER_NOT_EXIST(503),

    // 重复关注
    REPEAT_FOLLOW(504),

    // 重复取消关注
    REPEAT_REMOVE_FOLLOW(505),

    // 非法删除（如删除不属于自己的文章/八股/评论，或文章/八股/评论根本不存在）
    ILLEGAL_DELETION(506),

    // 非法上传（如为某id的文章/八股上传图片，但是对应id的文章/八股实际上不存在）
    ILLEGAL_UPLOAD(507),

    // 文章不存在
    ARTICLE_NOT_EXIST(508),

    // 重复点赞或重复取消点赞
    REPEAT_LIKE(509),

    // 父评论不存在
    PARENT_COMMENT_NOT_EXIST(510),

    // 传入 id 数量为 0
    NO_SELECT_ID(600),

    // 管理员登录异常，数据库没有对应信息或密码有无
    LOGIN_ERROR(601),

    // 数据库访问数据异常
    DATABASE_ERROR(602),

    // 传入数据类型有误
    DATA_TYPE_ERROR(603),

    // 传入 id 不存在
    MISSING_SELECT_ID(604),

    // 传入数据为空
    MISSING_DATA(605),

    // 传入时间格式有误
    DATE_FORMAT_ERROR(606);

    // 状态码
    private final int code;

    // 构造函数
    StatusCode(int code) {
        this.code = code;
    }

}
