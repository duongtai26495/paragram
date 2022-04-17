package net.accessory.paragram.entities;

public class ResponseObject {
private String status;
private String msg;
private Object result;

    public ResponseObject(String status, String msg, Object result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
