package diffbydevs.velog_clone.global.exception;

/**
 * 특정 권한이 필요한 경우 발생하는 예외입니다.
 * 기본적으로 HTTP 상태 코드 403에 대응됩니다.
 * @see org.springframework.http.HttpStatus#FORBIDDEN
 */
public class NoPermissionException extends AbstractBaseException {
	public NoPermissionException(String param, String errorMessage) {
		super(GlobalErrorCode.NO_PERMISSION, param, errorMessage);
	}
}
