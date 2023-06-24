package by.teachmeskills.utils;

import by.teachmeskills.exceptions.RequestParamNullException;

import java.util.Arrays;
import java.util.Objects;

public class HttpRequestParamValidatorUtils {
    private static final String REQUEST_PARAMETER_IS_NULL_ERROR = "Request parameter is not initialized!";

    public static void validateParametersNotNull(String... parameters) throws RequestParamNullException {
        if (Arrays.stream(parameters).anyMatch(Objects::isNull))
            throw new RequestParamNullException(REQUEST_PARAMETER_IS_NULL_ERROR);
    }
}
