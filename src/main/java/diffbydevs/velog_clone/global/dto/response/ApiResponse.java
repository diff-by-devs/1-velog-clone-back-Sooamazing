package diffbydevs.velog_clone.global.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/*
TODO
1. boolean + is 접두사인 경우, is 접두사는 생략됨 -> Boolean 말고 더 좋은 방식? Boolean으로 할 때, null 방지할 수 있는 방법?
2. 왜 여기는 기본 생성자가 없어도 될까?
3. results? errors? 단수 ? 복수? API마다는 객체/LIST 통일하는 게 낫겠지?
*/

/**
 * 일관적인 Rest API 응답을 만듭니다.
 * 성공, 실패 여부에 따라 다른 형태로 응답합니다.
 * @param <T> API 요청의 성공 결과 또는 오류 내용을 담는 타입
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {
	/**
	 * API 요청의 성공 여부, Json 직렬화 시 isSuccess로 반환하기 위해 Boolean 타입으로 선언.
	 */
	private final Boolean isSuccess;
	/**
	 * API 요청의 성공 결과
	 */
	private final T result;
	/**
	 * API 요청의 오류 내용
	 */
	private final T error;

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
	 * @param errorResponse API 요청의 오류 내용
	 * @return 오류 내용이 담긴 ApiResponse<Void>
	 */
	public static <T> ApiResponse<T> fail(T errorResponse) {
		return new ApiResponse<>(false, null, errorResponse);
	}
}
