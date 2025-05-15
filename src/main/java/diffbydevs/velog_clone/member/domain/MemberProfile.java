package diffbydevs.velog_clone.member.domain;

import diffbydevs.velog_clone.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
- 공부:
 1. 쿼리 확인. Lazy 먹히는지, 양방향으로 필요할지, 주인은 누구로 할지 고민.
 2. 소개에 Emoji 들어가는 걸 고려해 length validator 만들기
 - https://melonturtle.netlify.app/hibernate-column-size/
  -https://melonturtle.netlify.app/java-count-emoji/
 3. Pattern을 Entity 내부에 두는 게 적절할지 고민
 */

/**
 * 회원이 직접 수정 가능한 회원 정보입니다.
 * <p>
 *     가입일을 확인할 수 있습니다.
 * </p>
 *
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberProfile extends BaseEntity {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * 별명.
	 * slug로 사용, 영어 소문자, 숫자, - 사용 가능.
	 */
	@NotNull
	@Pattern(regexp = "^[a-z0-9-]*$")
	@Size(max = 50)
	@Column(length = 50, unique = true)
	private String nickname;

	/**
	 * 회원에 대한 간단한 소개
	 */
	private String introduce;

	/**
	 * 회원 가입하며 회원 프로필을 전부 작성합니다.
	 *
	 * @param member 회원
	 * @param nickname 별명
	 * @param introduce 회원 소개
	 * @return 회원 가입 시 작성한 프로필
	 */
	public static MemberProfile createProfile(Member member, String nickname, String introduce) {
		MemberProfile memberProfile = new MemberProfile();

		memberProfile.member = member;
		memberProfile.nickname = nickname;
		memberProfile.introduce = introduce;

		return memberProfile;
	}

	/**
	 * 회원 가입하며 회원 프로필에 별명만 작성합니다.
	 *
	 * @param member 회원
	 * @param nickname 별명
	 * @return 회원 가입 시 작성한 프로필
	 */
	public static MemberProfile createProfile(Member member, String nickname) {
		MemberProfile memberProfile = new MemberProfile();

		memberProfile.member = member;
		memberProfile.nickname = nickname;

		// 기본값
		memberProfile.introduce = null;

		return memberProfile;
	}
}
