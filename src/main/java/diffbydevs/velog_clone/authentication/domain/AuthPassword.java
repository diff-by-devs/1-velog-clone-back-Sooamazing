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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
 * 회원이 비밀 번호 형식으로 회원가입한 경우 사용합니다.
 * 암호화 방식을 적용해 비밀 번호를 저장합니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthPassword extends BaseEntityOnlyUpdatedAt {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * 회원 가입, 로그인 등 인증 시 사용하는 이메일 형식의 아이디
	 */
	@NotNull
	@Email
	@Column(unique = true, length = 150)
	private String accountId;

	/**
	 * SHA-512 단방향 암호화 적용한 비밀번호
	 */
	@NotNull
	@Size(max = 128)
	@Column(columnDefinition = "CHAR", length = 128)
	private String password;

	/**
	 * 암호화에 사용하는 salt 값
	 */
	@NotNull
	@Size(max = 128)
	@Column(columnDefinition = "CHAR", length = 128)
	private String salt;

	public static AuthPassword signUpWithPassword(
		Member member, String accountId,
		String password, String salt
	) {
		AuthPassword authPassword = new AuthPassword();

		authPassword.member = member;
		authPassword.accountId = accountId;
		authPassword.password = password;
		authPassword.salt = salt;

		return authPassword;
	}
}
