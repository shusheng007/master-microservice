package entity;

/**
 * Created by Ben.Wang on 2020/1/15.
 */
public enum StatusCode {
    OK(0,"成功"),
    FAILED(1,"未知错误")

    ;

    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message=message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
