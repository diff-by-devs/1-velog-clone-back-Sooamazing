package diffbydevs.velog_clone.member.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyUpdatedAt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
- query에서 Status에 check 제약도 함께 걸리는지 확인 필요.
- 회원 CRUD 진행 시 Profile 등 생성 방식 고민 필요.
 */

/**
 * 회원 Entity 중 근간이 되는 Entity. 
 * <p>
 *     회원에 대해 외부와 통신 시 해당 id로 통신합니다.
 * </p>
 */
@Getter
@Entity
@Table(name = "member_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseEntityOnlyUpdatedAt {
	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 회원 가입, 로그인 등 인증 시 사용하는 이메일 형식의 아이디
	 */
	@NotNull
	@Email
	@Column(unique = true, length = 150)
	private String accountId;

	@Column(columnDefinition = "tinyint(1) unsigned default 0")
	private int status;

	/**
	 * Member를 생성하는(회원 가입하는) 정적 팩토리 메서드.
	 * <p>
	 *     Member 생성은 오직 이 메서드로만 가능합니다.
	 * </p>
	 * @return Member 회원 가입한 Member
	 */
	public static Member signUp(String accountId) {
		Member member = new Member();

		member.accountId = accountId;
		member.status = MemberStatus.ACTIVE.getValueInDB();

		return member;
	}

}
