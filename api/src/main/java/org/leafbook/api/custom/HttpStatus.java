package org.leafbook.api.custom;

public enum HttpStatus {
    UNKNOWN_ERROR(0,"无法操作"),

    PARAMETER_ERROR(4000,"参数错误"),
    UNABLE_OPERATE(4001,"无法操作"),
    EMAIL_ALREADY_EXIST_ERROR(4002,"该邮箱已存在"),
    EMAIL_NOT_EXIST_ERROR(4003,"该邮箱未注册"),
    ENTRY_ALREADY_EXIST_ERROR(4004,"该词条已存在"),
    EMAIL_CODE_ERROR(4005,"验证码错误"),


    USER_BALANCE_INSUFFICIENT(5000,"余额不足"),
    USER_TOUCHED_STAR(5001,"已点过赞"),
    USER_TOUCHED_TREAD(5002,"已点过踩"),
    USER_ID_ILLEGAL(5003,"id不合法"),
    USER_PASSWORD_ILLEGAL(5004,"用户密码错误"),
    ;
    private int code;
    private String msg;

    HttpStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                '}';
    }
}
