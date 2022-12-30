package nhathm.jobhuntbe.aop;

import lombok.Data;

/**
 * @author nhathm on 2022-12-25
 */
@Data
public class ApiException extends Exception {
    private int code;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }
}
