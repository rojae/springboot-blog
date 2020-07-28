package com.rojae.blog.application.common.error;

public class updateTokenException extends RuntimeException{

    private static final long serialVersionUID = 4215240651059951505L;

    public updateTokenException() {
        super("토큰 업데이트 오류   ");
    }
}
