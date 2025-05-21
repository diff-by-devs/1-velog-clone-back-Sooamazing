package diffbydevs.velog_clone.global.presentation;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import diffbydevs.velog_clone.global.dto.response.ApiResponse;
import diffbydevs.velog_clone.global.exception.AbstractBaseException;
import diffbydevs.velog_clone.global.exception.AbstractUndiscoveredException;
import diffbydevs.velog_clone.global.exception.GlobalErrorCode;
import diffbydevs.velog_clone.global.exception.IErrorCode;
import diffbydevs.velog_clone.global.exception.LoginRequiredException;
import diffbydevs.velog_clone.global.exception.NoPermissionException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
/*
TODO
1. Exception, RuntimeException 어떤 걸 처리해야 할지, 둘 다 해야 할지 고민. 우선, RuntimeException으로 한 후, EXception해야 할 게 생기면 그때 확인해 보기로.
2. slf4j-api 사용 위해 따로 종속성 추가 안 해도 되나? lombok에서 제공하는 것과 뭐가 다르지?
3. mapToApiResponse? toApiResponse? map 이름을 단건 변환에 넣는 게 적절할까? -> 이건 반환값이 get으로는 적절하지 않아서 getApiResponseBy로 변경. by가 적절할까?

 */

/**
 * 모든 Controller 에서 발생하는 예외를 처리하기 위한 클래스입니다.
 * 기본적으로 에러를 던진 곳에서 debug 로그를 남깁니다.
 */
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 예상하지 못한 RuntimeException을 처리합니다.
	 * @param exception 예상하지 못한 RuntimeException
	 * @return ApiResponse<ErrorResponse> 객체
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(exception = RuntimeException.class)
	public ApiResponse<ErrorResponse> handleRuntimeException(RuntimeException exception) {
		log.error("[RuntimeException] : {}", exception);
		return ApiResponse.fail(ErrorResponse.createErrorResponseWithErrorCode(
			GlobalErrorCode.INTERNAL_SERVER, null)
		);
	}

	/**
	 * ExceptionHandler에서 구체적으로 정의하지 않은 BaseException을 처리합니다.
	 * 기본적으로는 모두 정의되어 있어야 합니다.
	 *
	 * @param exception ExceptionHandler에서 구체적으로 정의하지 않은 BaseException
	 * @return ApiResponse<ErrorResponse> 객체
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(exception = AbstractBaseException.class)
	public ApiResponse<ErrorResponse> handleBaseException(AbstractBaseException exception) {
		log.error("[BaseException] : {}", exception);
		return createApiResponse(exception);
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(exception = LoginRequiredException.class)
	public ApiResponse<ErrorResponse> handleLoginRequiredException(LoginRequiredException exception) {
		return createApiResponse(exception);
	}

	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(exception = NoPermissionException.class)
	public ApiResponse<ErrorResponse> handleNoPermissionException(NoPermissionException exception) {
		return createApiResponse(exception);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(exception = AbstractUndiscoveredException.class)
	public ApiResponse<ErrorResponse> handleUndiscoveredException(AbstractUndiscoveredException exception) {
		return createApiResponse(exception);
	}

	/**
	 * AbstractBaseException을 상속받은 예외로 ApiResponse를 생성합니다.
	 * @param exception AbstractBaseException 구현 예외
	 * @return ApiResponse<ErrorResponse> 객체
	 */
	private ApiResponse<ErrorResponse> createApiResponse(AbstractBaseException exception) {
		return ApiResponse.fail(
			ErrorResponse.createErrorResponseWithErrorCode(
				exception.getIErrorCode(),
				exception.getParam()
			)
		);
	}

	/**
	 * &#064;Valid  어노테이션을 사용하여 검증할 때 발생하는 예외를 처리합니다.
	 * 여러 개의 필드에서 에러가 발생할 수 있기에 List로 반환합니다.
	 *
	 * @param exception MethodArgumentNotValidException
	 * @return ApiResponse<List < ErrorResponse>> 객체
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ApiResponse<List<ErrorResponse>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException exception) {

		List<ErrorResponse> errorList = exception.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(
				ErrorResponse::createValidationErrorResponseWithFieldError)
			.toList();

		return ApiResponse.fail(errorList);
	}

	/**
	 * API 요청 실패 시 오류 정보를 담습니다.
	 */
	@Getter
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public static class ErrorResponse {
		/**
		 * 오류를 식별할 수 있는 오류 이름
		 */
		private final String name;
		/**
		 * 오류에 대한 설명
		 */
		private final String message;
		/**
		 * 사용자가 보낸 내용 중 오류가 발생한 파라미터
		 */
		private final String param;

		/**
		 * ErrorCode에 정의된 오류 이름과 메시지를 사용하여 ErrorResponse를 생성합니다.
		 * @param IErrorCode 오류 코드
		 * @param param 오류가 발생한 파라미터
		 * @return ErrorResponse 객체
		 */
		public static ErrorResponse createErrorResponseWithErrorCode(IErrorCode IErrorCode, String param) {
			return new ErrorResponse(IErrorCode.name(), IErrorCode.getMessage(), param);
		}

		/**
		 * &#064;Valid 어노테이션 검증 오류 값을 이용해 ErrorResponse를 생성합니다.
		 * @param fieldError 해당 필드에서 발생한 오류
		 * @return ErrorResponse 객체
		 */
		public static ErrorResponse createValidationErrorResponseWithFieldError(FieldError fieldError) {
			return new ErrorResponse(GlobalErrorCode.INVALID_REQUEST.name(),
				fieldError.getDefaultMessage(), fieldError.getField());
		}
	}
}
