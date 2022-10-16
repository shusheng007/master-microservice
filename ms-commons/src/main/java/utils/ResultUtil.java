package utils;

import entity.BaseResponse;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 22:52
 * @description:
 */
public class ResultUtil {

    public static <T> BaseResponse ok(T data) {
        return new BaseResponse(data);
    }

    public static BaseResponse error(int code, String errorMessage) {
        return new BaseResponse(code, errorMessage);
    }

    public static BaseResponse error(String errorMessage) {
        return new BaseResponse(1, errorMessage);
    }


}
