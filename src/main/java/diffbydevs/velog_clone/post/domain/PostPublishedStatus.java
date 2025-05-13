package diffbydevs.velog_clone.post.domain;

import lombok.Getter;

/**
 * 게시글의 발행 상태입니다.
 * <p>
 *     코드의 가독성을 위해 어플리케이션 코드에서만 사용합니다.
 *     실제 db에서는 정규화 문제로 ENUM이 아닌 정수형으로 관리합니다.
 * </p>
 * @see Post#publishedStatus
 */
@Getter
public enum PostPublishedStatus {
	/**
	 * 임시 저장
	 */
	DRAFT(0),
	/**
	 * 발행
	 */
	PUBLISHED(1);

	/**
	 * 실제로 db에 들어가게 되는 값
	 */
	private final int valueInDB;

	PostPublishedStatus(int valueInDB) {
		this.valueInDB = valueInDB;
	}
}
