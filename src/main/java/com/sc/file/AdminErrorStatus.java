package com.sc.file;

public enum AdminErrorStatus {

    STATUS_1001(1501, "内置的系统管理员不能修改。"),
    STATUS_1502(1502, "只支持上传以下格式的图片：%s"),
    STATUS_1503(1503, "上传图片的文件大小不能超过200KB"),
    STATUS_1504(1504, "只能删除未上架的拼团活动"),


    STATUS_1599(1599, "");

    AdminErrorStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

