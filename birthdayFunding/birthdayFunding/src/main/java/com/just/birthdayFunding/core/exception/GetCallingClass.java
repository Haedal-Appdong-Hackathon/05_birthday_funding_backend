package com.just.birthdayFunding.core.exception;

import org.springframework.stereotype.Component;

@Component
public class GetCallingClass {
    public String call(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length > 0) {
            return stackTrace[0].getClassName();
        }
        return "Unknown";
    }
}
