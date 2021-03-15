package com.example.bbs.common.constant;

import lombok.extern.slf4j.Slf4j;

/**
 * 每个业务，100个范围值就够了.
 */
@Slf4j
public class ResponseCodeConst {

    public static final ResponseCodeConst SUCCESS = new ResponseCodeConst(1, "操作成功!", true);
    public static final ResponseCodeConst FALSE = new ResponseCodeConst(2, "操作失败!", false);

    protected int code;

    protected String msg;

    protected boolean success;

    public ResponseCodeConst() {
    }

    protected ResponseCodeConst(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    protected ResponseCodeConst(int code, String msg, boolean success) {
        super();
        this.code = code;
        this.msg = msg;
        this.success = success;
    }

    protected ResponseCodeConst(int code) {
        super();
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static void init() {
        log.info("ResponseCodeConst init....");
    }


}
