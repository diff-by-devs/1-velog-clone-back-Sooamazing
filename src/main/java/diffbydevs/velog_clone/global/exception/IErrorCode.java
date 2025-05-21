package diffbydevs.velog_clone.global.exception;

/**
 * ErrorCode 유형을 관리하기 위한 에러 코드 인터페이스입니다.
 * enum에서 상속받아 사용합니다.
 */
public interface IErrorCode {
	String name();

	String getMessage();
}
