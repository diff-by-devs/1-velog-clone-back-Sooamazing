package diffbydevs.velog_clone.post.domain;

import lombok.Getter;

/**
 * 게시글의 공개 상태를 나타냅니다.
 * <p>
 *     코드의 가독성을 위해 어플리케이션 코드에서만 사용합니다.
 *     실제 db에서는 정규화 문제로 ENUM이 아닌 정수형으로 관리합니다.
 * </p>
 */
@Getter
public enum PostPublicStatus {
	/**
	 * 비공개
	 */
	PRIVATE(0),
	/**
	 * 공개
	 */
	PUBLIC(1);

	/**
	 * 실제로 db에 들어가게 되는 값
	 */
	private final int valueInDB;

	PostPublicStatus(int valueInDB) {
		this.valueInDB = valueInDB;
	}
}
