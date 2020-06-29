package com.rojae.blog.application.utility;

import java.util.Map;

public class ObjectUtils {
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
            return true;
        }
        if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0);
        }
        return false;
    }
}
