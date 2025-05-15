package diffbydevs.velog_clone.global.exception;

import lombok.Getter;

/*
TODO Exception, RuntimeException 두 개의 차이는? 만약 Exception 상속 받았따면 어떤 게 달라질지?
 */

/**
 * 어플리케이션에서 발생하는 예외를 처리하기 위한 기본 클래스입니다.
 * 어플리케이션 예외를 명확하게 하기 위해 상속받아 사용합니다.
 */
@Getter
public abstract class BaseException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String param;

	protected BaseException(ErrorCode errorCode, String param, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.param = param;
	}
}
