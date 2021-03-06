package com.sarf.springmvc.error;

import java.util.Set;

public class UnSupportedFieldPatchException extends RuntimeException{

    public UnSupportedFieldPatchException(Set<String> keys) {
        super("Update is not allowed on fields " + keys.toString());
    }
}
