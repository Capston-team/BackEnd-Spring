package study.wheretogo.exception;

public class InvalidParameterException extends CommonException {

    public InvalidParameterException(String parameterName) {
        super(parameterName + " - 해당 파라미터가 비어있거나 null 입니다.");
    }
}

