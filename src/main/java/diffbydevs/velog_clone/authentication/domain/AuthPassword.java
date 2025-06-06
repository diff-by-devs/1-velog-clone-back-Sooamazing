package diffbydevs.velog_clone.authentication.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyUpdatedAt;
import diffbydevs.velog_clone.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
/*
TODO
1. salt 정책
2. SHA-512 단방향 암호화 방식 사용?
3. 비밀번호, Salt 길이 적절성 확인
4. TODO 검증을 생성할 때 파라미터에도 넣어야 하는 걸까? 우선 값 객체에 넣는 방식을 고민해 보자.
 */

/**
 * 비밀 번호 형식으로 회원 가입한 회원의 인증 정보입니다.
 * 암호화 방식을 적용해 비밀 번호를 저장하고, 로그인 시 확인합니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthPassword extends BaseEntityOnlyUpdatedAt {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * SHA-512 단방향 암호화 적용한 비밀번호
	 */
	@NotNull
	@Size(max = 128)
	@Column(columnDefinition = "CHAR(128)")
	private String password;

	/**
	 * 암호화에 사용하는 salt 값
	 */
	@NotNull
	@Size(max = 128)
	@Column(columnDefinition = "CHAR(128)")
	private String salt;

	/**
	 * 비밀 번호로 회원 가입합니다.
	 *
	 * @param member (방금 가입한) 회원
	 * @param password 비밀번호
	 * @return 비밀번호 방식으로 가입한 회원 정보
	 */
	public static AuthPassword signUpWithPassword(
		Member member, String password
	) {
		AuthPassword authPassword = new AuthPassword();

		authPassword.member = member;
		authPassword.password = password; // TODO 암호화 진행 후 저장
		authPassword.salt = "salt"; // TODO 암호화 방식이 정해지면, 내부에서 salt 값 생성 후 저장하기로.

		return authPassword;
	}
}
