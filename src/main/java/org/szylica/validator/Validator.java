package org.szylica.validator;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T t);

    static <T> boolean validate(T data, Validator<T> validator) {
        return validator.validate(data).isEmpty();
    }
}
