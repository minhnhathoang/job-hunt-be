package nhathm.jobhuntbe.payload.request;

import lombok.Data;

/**
 * @author nhathm on 2022-12-25
 */
@Data
public class BaseResponse {
    private int code;
    private String message;
    private Object data;

    public BaseResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResponse(int code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.data = object;
    }

    public static BaseResponse success() {
        return new BaseResponse(200, "Success");
    }
}
