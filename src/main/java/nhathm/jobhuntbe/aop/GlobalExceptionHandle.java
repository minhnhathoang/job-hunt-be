package nhathm.jobhuntbe.aop;

import nhathm.jobhuntbe.payload.request.BaseResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandle.class);
    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleCustomizedException(ApiException e) {
        LOGGER.info("Exception {}", e.getMessage());
        e.printStackTrace();
        return new ResponseEntity<>(new BaseResponse(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<BaseResponse> handleCustomizedException(Exception e) {
        e.printStackTrace();
        LOGGER.info("Exception {}", e.getMessage());
        return new ResponseEntity<>(new BaseResponse(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

