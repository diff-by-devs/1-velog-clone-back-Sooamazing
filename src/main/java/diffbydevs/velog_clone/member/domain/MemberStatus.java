package diffbydevs.velog_clone.member.domain;

import lombok.Getter;

/**
 * 회원의 활성 상태입니다.
 * <p>
 *     코드의 가독성을 위해 어플리케이션 코드에서만 사용합니다.
 *     실제 db에서는 정규화 문제로 ENUM이 아닌 정수형으로 관리합니다.
 * </p>
 * @see Member#status
 */
@Getter
public enum MemberStatus {
	/**
	 * 활성 상태, 회원 가입 이후~휴면/탈퇴 전까지의 상태
	 */
	ACTIVE(0),
	/**
	 * 휴면 상태
	 */
	DORMANT(1),
	/**
	 * 탈퇴 상태
	 */
	WITHDRAWAL(2);

	/**
	 * 실제로 db에 들어가게 되는 값
	 */
	private final int valueInDB;

	MemberStatus(int valueInDB) {
		this.valueInDB = valueInDB;
	}
}
