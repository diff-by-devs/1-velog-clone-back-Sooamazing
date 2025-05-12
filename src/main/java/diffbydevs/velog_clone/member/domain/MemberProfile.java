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
import lombok.AllArgsConstructor;
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
 * 회원이 직접 수정 가능한 프로필.
 * <p>
 *     가입일을 확인할 수 있습니다.
 * </p>
 *
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberProfile extends BaseEntity {
	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * 별명.
	 * slug로도 사용됨.
	 * 영어 소문자, 숫자, - 사용 가능.
	 */
	@NotNull
	@Pattern(regexp = "^[a-z0-9-]*$")
	@Size(max = 50)
	@Column(length = 50, unique = true)
	private String nickname;

	private String introduce;

	/**
	 * 회원의 프로필을 최초로 작성하는(MemberProfile 생성) 정적 팩토리 메서드.
	 * <p>
	 *     회원의 프로필 최초 작성은 오직 이 메서드로만 가능합니다.
	 * </p>
	 * @param member 회원
	 * @param nickname 별명
	 * @param introduce 회원 소개
	 * @return 가입한 회원이 작성한 프로필
	 */
	public static MemberProfile createProfile(Member member, String nickname, String introduce) {
		MemberProfile memberProfile = new MemberProfile();

		memberProfile.member = member;
		memberProfile.nickname = nickname;
		memberProfile.introduce = introduce;

		return memberProfile;
	}
}
