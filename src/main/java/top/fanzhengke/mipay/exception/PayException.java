package top.fanzhengke.mipay.exception;

import lombok.Data;

@Data
public class PayException extends RuntimeException {
    private String msg;
    private Integer code = 500;

    public PayException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public PayException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public PayException(String msg, Integer code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public PayException(String msg, Integer code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
