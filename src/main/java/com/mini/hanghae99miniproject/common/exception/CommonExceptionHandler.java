package com.mini.hanghae99miniproject.common.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.mini.hanghae99miniproject.common.exception.ExceptionMessage.*;


@RestControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler({IllegalArgumentException.class})
    public ExceptionResponse handleBadRequest(IllegalArgumentException e) {

        if (e.getMessage().equals(NO_EXIST_POSTING_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(NO_EXIST_POSTING_ERROR_MSG);
        }
        if (e.getMessage().equals(NO_EXIST_COMMENT_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(NO_EXIST_COMMENT_ERROR_MSG);
        }
        if (e.getMessage().equals(ADMIN_TOKEN_MISMATCH_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(ADMIN_TOKEN_MISMATCH_ERROR_MSG);
        }
        if (e.getMessage().equals(DUPLICATE_USER_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(DUPLICATE_USER_ERROR_MSG);
        }
        if (e.getMessage().equals(USER_NOT_FOUND_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(USER_NOT_FOUND_ERROR_MSG);
        }
        if (e.getMessage().equals(PASSWORDS_DO_NOT_MATCH_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(PASSWORDS_DO_NOT_MATCH_ERROR_MSG);
        }
        if (e.getMessage().equals(DUPLICATE_NICK_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(DUPLICATE_NICK_ERROR_MSG);
        }
        if (e.getMessage().equals(USER_NOT_MATCH_ERROR_MSG.getMsg())) {
            return new ExceptionResponse(USER_NOT_MATCH_ERROR_MSG);
        }
        return new ExceptionResponse(INTERNAL_SERVER_ERROR_MSG);
    }

    //@Valid 예외처리
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ExceptionResponse handleBadRequest(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            //
            if (fieldError.getField().equals("userid")) {
                return new ExceptionResponse(fieldError.getField() + " 필드는 이메일 형식으로 작성하셔야 합니다.", 400);
                // 올바른 이메일 형식이 아닙니다
                // 올바른 이메일 형식입니다.
            }
            //
            return new ExceptionResponse(fieldError.getField() + " 필드는 최소 8자 이상, 하나 이상의 대문자 또는 소문자와 하나의 숫자 및 하나의 특수 문자가 들어가야합니다.", 400);
            // 숫자+영문자_특수문자 조합으로 8자리 이상 입력해주세요!
            // 안전한 비밀번호 입니다.
        }
        return new ExceptionResponse(INTERNAL_SERVER_ERROR_MSG);
    }
}
