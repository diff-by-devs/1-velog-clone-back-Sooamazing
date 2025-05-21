package diffbydevs.velog_clone.global.exception;

import java.util.Map;
/*
TODO
1. Guava ImmutableMap 사용 고려?
2. Map.of 코드를 잘못 적으면 RUNTIME ERRor 발생하는데, 코드로 사용하지 못하게 해결할 수 있는 방법? 설계?
 */

/**
 * 공통으로 사용되는 Validation Error Message를 관리합니다.
 */
public final class ValidationErrorMessageUtil {

	public static final String EMAIL = "Email";
	public static final String MAX_WITH_FINAL_CONSONANTS = "MaxLengthWithFinalConsonants";
	public static final String MAX_NO_FINAL_CONSONANTS = "MaxLengthNoFinalConsonants";
	public static final String MIN_WITH_FINAL_CONSONANTS = "MinLengthWithFinalConsonants";
	public static final String MIN_NO_FINAL_CONSONANTS = "MinLengthNoFinalConsonants";
	public static final String NOT_NULL_WITH_FINAL_CONSONANTS = "NotNullWithFinalConsonants";
	public static final String NOT_NULL_NO_FINAL_CONSONANTS = "NotNullNoFinalConsonants";
	public static final String NOT_EMPTY_WITH_FINAL_CONSONANTS = "NotEmptyWithFinalConsonants";
	public static final String NOT_EMPTY_NO_FINAL_CONSONANTS = "NotEmptyNoFinalConsonants";
	public static final String NOT_BLANK_WITH_FINAL_CONSONANTS = "NotBlankWithFinalConsonants";
	public static final String NOT_BLANK_NO_FINAL_CONSONANTS = "NotBlankNoFinalConsonants";
	public static final String NULL_WITH_FINAL_CONSONANTS = "NullWithFinalConsonants";
	public static final String NULL_NO_FINAL_CONSONANTS = "NullNoFinalConsonants";
	public static final String PATTERN_PASSWORD = "PatternPassword";
	public static final String SIZE_WITH_FINAL_CONSONANTS = "SizeWithFinalConsonants";
	public static final String SIZE_NO_FINAL_CONSONANTS = "SizeNoFinalConsonants";
	private ValidationErrorMessageUtil() {
	}

	/**
	 * Validation Error Message를 관리하는 불변 Map입니다.
	 * 수정 시 UnSupportedOperationException이 발생합니다.
	 */
	public static final Map<String, String> immutableMessageMap = Map.ofEntries(
		Map.entry(EMAIL, "에 올바른 형식의 이메일 주소를 입력해 주세요."),
		Map.entry(PATTERN_PASSWORD, "는 영문 대문자, 소문자, 숫자, 특수문자를 포함하여 8자 이상 입력해 주세요."),

		// 받침 유무
		Map.entry(MAX_WITH_FINAL_CONSONANTS, "은 최대 {value}까지 혀용합니다."),
		Map.entry(MAX_NO_FINAL_CONSONANTS, "는 최대 {value}까지 혀용합니다."),
		Map.entry(MIN_WITH_FINAL_CONSONANTS, "은 {value} 이상이어야 합니다."),
		Map.entry(MIN_NO_FINAL_CONSONANTS, "는 {value} 이상이어야 합니다."),
		Map.entry(NOT_NULL_WITH_FINAL_CONSONANTS, "은 필수입니다."),
		Map.entry(NOT_NULL_NO_FINAL_CONSONANTS, "는 필수입니다."),
		Map.entry(NOT_EMPTY_WITH_FINAL_CONSONANTS, "은 필수입니다. 공백을 제외한 값을 입력해 주세요."),
		Map.entry(NOT_EMPTY_NO_FINAL_CONSONANTS, "\"는 필수입니다. 공백을 제외한 값을 입력해 주세요."),
		Map.entry(NOT_BLANK_WITH_FINAL_CONSONANTS, "은 필수입니다. 공백을 제외한 값을 입력해 주세요."),
		Map.entry(NOT_BLANK_NO_FINAL_CONSONANTS, "는 필수입니다. 공백을 제외한 값을 입력해 주세요."),
		Map.entry(NULL_WITH_FINAL_CONSONANTS, "은 비어 있어야 합니다."),
		Map.entry(NULL_NO_FINAL_CONSONANTS, "는 비어 있어야 합니다."),
		Map.entry(SIZE_WITH_FINAL_CONSONANTS, "은 {min} ~ {max} 사이로 입력해 주세요."),
		Map.entry(SIZE_NO_FINAL_CONSONANTS, "는 {min} ~ {max} 사이로 입력해 주세요.")
	);
}
