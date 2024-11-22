package com.qilicoh.boot.domain.model;
import com.qilicoh.boot.common.constant.HttpStatus;
import com.qilicoh.boot.common.enums.ResultCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import java.util.Objects;

public class Response extends HashMap<String, Object> {
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    public static final String SUCCESS_MSG = "操作成功";
    public static final String ERROR_MSG = "操作失败";

    public Response() {
    }

    public Response(int code, String msg, Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        super.put(DATA, data);
    }

    public Response(ResultCodeEnum resultCodeEnum) {
        super.put(CODE, resultCodeEnum.getCode());
        super.put(MSG, resultCodeEnum.getMsg());
        super.put(DATA, null);
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static Response success() {
        return new Response(HttpStatus.SUCCESS, SUCCESS_MSG, null);
    }

    public static Response success(Object data) {
        return new Response(HttpStatus.SUCCESS, SUCCESS_MSG, data);
    }

    public static Response success(String msg, Object data) {
        return new Response(HttpStatus.SUCCESS, msg, data);
    }

    public static Response error() {
        return new Response(HttpStatus.ERROR, ERROR_MSG, null);
    }

    public static Response error(String msg) {
        return new Response(HttpStatus.ERROR, msg, null);
    }

    public static Response error(String msg, Object data) {
        return new Response(HttpStatus.ERROR, msg, null);
    }

    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE));
    }

    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE));
    }

    public static DataMap dataMap() {
        Response response = new Response(HttpStatus.SUCCESS, SUCCESS_MSG, null);
        DataMap dataMap = new DataMap(response);
        response.put(DATA, dataMap);
        return dataMap;
    }

    public static class DataMap extends HashMap<String, Object> {
        @JsonIgnore
        private Response response;

        public DataMap() {

        }

        public DataMap(Response response) {
            this.response = response;
        }

        @Override
        public DataMap put(String key, Object value) {
            super.put(key, value);
            return this;
        }

        public DataMap getMap(String key) {
            DataMap dataMap = new DataMap(response);
            put(key, dataMap);
            return dataMap;
        }

        public void setResponse(Response response) {
            this.response = response;
        }

        public Response ok() {
            return this.response;
        }
    }
}
