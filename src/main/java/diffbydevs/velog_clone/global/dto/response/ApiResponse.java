package diffbydevs.velog_clone.global.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import diffbydevs.velog_clone.global.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
TODO
1. getter 빼고 만들어 보기. 역직렬화 확인.
2. 왜 여기는 기본 생성자가 없어도 될까?
*/

/**
 * 일관적인 Rest API 응답을 만듭니다.
 * 성공, 실패 여부에 따라 다른 형태로 응답합니다.
 * @param <T> 성공 응답 데이터 타입
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	/**
	 * API 요청의 성공 여부
	 */
	private final boolean isSuccess;
	/**
	 * API 요청의 성공 결과
	 */
	private final T result;
	/**
	 * API 요청의 오류 내용
	 */
	private final ErrorResponse error;

	/**
	 * API 요청 성공 시 성공 결과를 반환하기 위해 사용합니다.
	 * @param result API 요청의 성공 결과
	 * @return 성공 결과가 담긴 ApiResponse<T>
	 */
	public static <T> ApiResponse<T> success(T result) {
		return new ApiResponse<>(true, result, null);
	}

	/**
	 * API 요청 실패 시 오류를 알려주기 위해 사용합니다.
	 * @param errorCode API 요청의 오류 코드
	 * @param param API 요청에서 오류가 발생한 파라미터
	 * @return 오류 내용이 담긴 ApiResponse<Void>
	 */
	public static <Void> ApiResponse<Void> fail(ErrorCode errorCode, String param) {
		return new ApiResponse<>(false, null, new ErrorResponse(errorCode.name(), errorCode.getMessage(), param));
	}

	/**
	 * API 요청 실패 시 오류를 알려주기 위해 사용합니다.
	 */
	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	private static class ErrorResponse {
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
	}
}
