package com.pavel.forumapplication.Exception;

import com.pavel.forumapplication.Model.ErrorModel;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Controller
public class ErrorControllerImpl implements ErrorController {

    private static final String ERROR = "/error";

    private final ErrorAttributes errorAttributes;

    public ErrorControllerImpl(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(ERROR)
    public ResponseEntity<ErrorModel> error(WebRequest request) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(
                request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.EXCEPTION, ErrorAttributeOptions.Include.MESSAGE)
        );

        return ResponseEntity
                .status((Integer) attributes.get("status"))
                .body(ErrorModel
                        .builder()
                        .error((String) attributes.get("error"))
                        .error_description((String) attributes.get("message"))
                        .build()
                );
    }
}