package org.leafbook.api.custom;

public enum HttpStatus {
    PARAMETER_ERROR(4000,"参数错误"),
    UNABLE_OPERATE(4001,"无法操作"),
    UNKNOWN_ERROR(0,"无法操作"),
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
