package com.qilicoh.boot.common.enums;

import com.qilicoh.boot.common.constant.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {
    SUCCESS(HttpStatus.SUCCESS, "操作成功"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "参数列表错误"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "未授权"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "权限不足"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "资源未找到"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "不允许的http方法"),
    ERROR(HttpStatus.ERROR, "系统内部错误"),
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED, "接口未实现"),

    NOT_TOKEN(1001, "未能读取到有效 token"),
    INVALID_TOKEN(1002, "token 无效"),
    TOKEN_TIMEOUT(1003, "token 已过期"),
    NO_PREFIX(1004, "未按照指定前缀提交 token"),
    NO_LOGIN(1005, "当前会话未登录"),

    USERNAME_NOT_FOUNT(1101, "用户名不存在"),
    USERNAME_EXIST(1102, "用户名已被使用"),
    PASSWORD_ERROR(1103, "密码错误"),

    FILE_NOT_FOUND(2000, "文件不存在或异常"),
    FILE_UPLOAD_ERROR(2001, "文件上传失败"),
    FILE_UPLOAD_FORBID(2002, "文件格式不支持或重复上传同名文件"),
    FILE_DOWNLOAD_ERROR(2003, "文件下载异常"),
    FILE_MD5_CALCULATION_ERROR_EXCEPTION(2004, "文件 md5 计算错误"),

    REPOSITORY_NOT_FOUND(3001, "仓库不存在"),
    REPOSITORY_FORBIDDEN(3002, "仓库权限不足"),
    FOLDER_FORBIDDEN(3003, "文件夹权限不足");

    private final int code;
    private final String msg;
}
