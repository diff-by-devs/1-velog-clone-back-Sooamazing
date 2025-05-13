package diffbydevs.velog_clone.comment.domain;

import lombok.Getter;

/**
 * 댓글의 계층을 나타냅니다. 댓글, 대댓글, 대대댓글이 있습니다.
 * <p>
 *     코드의 가독성을 위해 어플리케이션 코드에서만 사용합니다.
 *     실제 db에서는 정규화 문제로 ENUM이 아닌 정수형으로 관리합니다.
 * </p>
 * @see Comment#level
 */
@Getter
public enum CommentLevel {

	/**
	 * 댓글, 최상위 댓글
	 */
	COMMENT(0),
	/**
	 * 대댓글
	 */
	REPLY(1),
	/**
	 * 대대댓글
	 */
	LAST_REPLY(2);

	/**
	 * 실제로 db에 들어가게 되는 값
	 */
	private final int valueInDB;

	CommentLevel(int valueInDB) {
		this.valueInDB = valueInDB;
	}
}
