package diffbydevs.velog_clone.member.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyUpdatedAt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
공부용 기록
- 이메일: 사용자의 99퍼가 63자 이내로 사용한다고 해 150으로 설정.
- homepage: bit.ly 등 줄일 방법이 다양해 최대 255로 설정.
- 기본값 255는 따로 기입하지 않음.
- 최초 작성할 때 create, make 등을 사용한다고도 해서 create로 사용.
- id는 다른 홈페이지 제한 모르겠어서 그냥 255로 둠.

TODO
1. id, username, homepage url은 어떤 제약 조건이 들어가면 될까..?
 */

/**
 * 회원이 직접 입력하고, 외부에 보이는 social 정보
 *
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberSocial extends BaseEntityOnlyUpdatedAt {
	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * social 정보로 노출할 email. 이 이메일은 인증과는 관련 없음.
	 */
	@Email
	@Size(max = 150)
	@Column(length = 150)
	private String email;

	/**
	 * github에서 @ㅇㅇㅇ으로 사용하는 id
	 */
	@Size(max = 255)
	private String githubId;

	/**
	 * twitter에서 @ㅇㅇㅇ으로 사용하는 id
	 */
	@Size(max = 255)
	private String twitterId;

	/**
	 * www.facebook.com/ㅇㅇㅇ 여기서 ㅇㅇㅇ이 username
	 */
	@Size(max = 255)
	private String facebookUsername;

	/**
	 * 보여주고 싶은 Homepage url
	 */
	@Size(max = 255)
	private String homepage;

	/**
	 * 회원의 social 정보를 최초로 작성하는(MemberSocial 생성) 정적 팩토리 메서드.
	 *
	 * @param member 회원
	 * @param email email
	 * @param githubId github에서 사용하는 id
	 * @param twitterId twitter에서 사용하는 id
	 * @param facebookUsername facebook uri 뒤에 붙은 username
	 * @param homepage 보여주고 싶은 홈페이지
	 * @return MemberSocial 회원의 소셜 정보
	 */
	public static MemberSocial createMemberSocial(
		Member member, String email, String githubId, String twitterId
		, String facebookUsername, String homepage
	) {
		MemberSocial memberSocial = new MemberSocial();

		memberSocial.member = member;
		memberSocial.email = email;
		memberSocial.githubId = githubId;
		memberSocial.twitterId = twitterId;
		memberSocial.facebookUsername = facebookUsername;
		memberSocial.homepage = homepage;

		return memberSocial;
	}
}
