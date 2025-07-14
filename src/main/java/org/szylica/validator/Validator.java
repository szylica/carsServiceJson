package org.szylica.validator;

import java.util.Map;

public interface Validator<T> {
    Map<String, String> validate(T t);
}
