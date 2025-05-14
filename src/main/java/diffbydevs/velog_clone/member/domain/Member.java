package diffbydevs.velog_clone.member.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyUpdatedAt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
- query에서 Status에 check 제약도 함께 걸리는지 확인 필요.
- 회원 CRUD 진행 시 Profile 등 생성 방식 고민 필요.
 */

/**
 * 회원의 메타 데이터입니다.
 * <p>
 *     회원에 대해 외부와 통신 시 해당 id로 통신합니다.
 * </p>
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntityOnlyUpdatedAt {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 회원 가입, 로그인 등 인증 시 사용하는 이메일 형식의 아이디
	 */
	@NotNull
	@Email
	@Column(unique = true, length = 150)
	private String accountId;

	/**
	 * 회원의 활성, 휴면, 탈퇴 상태
	 * @see MemberStatus
	 */
	@Column(columnDefinition = "tinyint(1) unsigned default 0")
	private int status;

	/**
	 * 회원 가입합니다.
	 * @return 회원 가입한 회원의 메타 데이터
	 */
	public static Member signUp(String accountId) {
		Member member = new Member();

		member.accountId = accountId;
		member.status = MemberStatus.ACTIVE.getValueInDB();

		return member;
	}

}
