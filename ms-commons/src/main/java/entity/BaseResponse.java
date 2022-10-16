package entity;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 22:28
 * @description:
 */

public class BaseResponse<T> {
    private int code;
    private String errorMessage;
    private T data;

    public BaseResponse(int code, String errorMessage, T data) {
        this.code = code;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public BaseResponse(T data) {
        this(StatusCode.OK.getCode(), "",data);
    }

    public BaseResponse(int code, String errorMessage) {
        this(code,errorMessage,null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
