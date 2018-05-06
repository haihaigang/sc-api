package com.sc.file;

import com.alibaba.fastjson.JSON;

/**
 * Api响应消息体
 * Created by xyqin on 16/5/6.
 */
public class ApiEntity<T> {

    /**
     * 当前服务器时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 状态码(参考com.meigo.core.api.ApiStatus)
     */
    private int status;

    /**
     * 提示消息
     */
    private String message;

    /**
     * 服务ID
     */
    private String service;

    /**
     * 接口路径
     */
    private String path;

    /**
     * 响应内容
     */
    private T body;

    public ApiEntity() {
        this.status = ApiStatus.STATUS_200.getCode();
        this.message = ApiStatus.STATUS_200.getMessage();
    }

    public ApiEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ApiEntity(int status, String message, String service, String path) {
        this.status = status;
        this.message = message;
        this.service = service;
        this.path = path;
    }

    public ApiEntity(ApiStatus status) {
        this.status = status.getCode();
        this.message = status.getMessage();
    }

    public ApiEntity(ApiStatus status, String service, String path) {
        this.status = status.getCode();
        this.message = status.getMessage();
        this.service = service;
        this.path = path;
    }

    public ApiEntity(T body) {
        this.status = ApiStatus.STATUS_200.getCode();
        this.message = ApiStatus.STATUS_200.getMessage();
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        String format = "status: %s, message: %s, service: %s, path: %s, body: %s";
        return String.format(format, status, message, service, path, JSON.toJSONString(body));
    }

}
