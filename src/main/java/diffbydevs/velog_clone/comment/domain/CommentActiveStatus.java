package diffbydevs.velog_clone.comment.domain;

import lombok.Getter;

/**
 * 댓글 활성/삭제 상태를 나타냅니다.
 * <p>
 *     코드의 가독성을 위해 어플리케이션 코드에서만 사용합니다.
 *     실제 db에서는 정규화 문제로 ENUM이 아닌 정수형으로 관리합니다.
 * </p>
 * @see Comment#isDeleted
 */
@Getter
public enum CommentActiveStatus {

	/**
	 * 댓글 게시 중
	 */
	ACTIVE(0),
	/**
	 * 댓글 삭제됨
	 */
	DELETED(1);
	/**
	 * 실제로 db에 들어가게 되는 값
	 */
	private final int valueInDB;

	CommentActiveStatus(int valueInDB) {
		this.valueInDB = valueInDB;
	}
}
