package diffbydevs.velog_clone.blog.domain;

import diffbydevs.velog_clone.global.domain.BaseEntity;
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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
1. blog 이름 중복 시 정책 고민 필요. 존재하는 값이면 -UUID(6) 이런 식으로 생성?
 */

/**
 * 회원 가입시 자동으로 생성되는 블로그
 * <p>
 *     현재는 회원 한 명 당 하나만 생성 가능하다.
 * </p>
 *
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Blog extends BaseEntity {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	/**
	 * 블로그 이름
	 * slug로도 사용됨.
	 * 영어 소문자, 숫자, - 사용 가능.
	 */
	@NotNull
	@Pattern(regexp = "^[a-z0-9-]*$")
	@Size(max = 60)
	@Column(unique = true, length = 60)
	private String name;

	/**
	 * 블로그 소개
	 * <p>
	 *     회원 가입 시에는 작성하지 않음.
	 * </p>
	 */
	private String introduce;

	/**
	 * 블로그를 엽니다.
	 *
	 * @param member 가입한 회원
	 * @param name 블로그 이름
	 * @return Blog 생성된 블로그
	 */
	public static Blog openBlog(Member member, String name) {
		Blog blog = new Blog();

		blog.member = member;
		blog.name = name;

		return blog;
	}
}
