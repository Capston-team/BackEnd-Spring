package study.wheretogo.exception;

public class ResourceNotFoundException extends CommonException {

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " - 해당 파일 또는 코드를 찾을 수 없습니다.");
    }
}
