package diffbydevs.velog_clone.post.domain;

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
1. brieflyIntroduce 는 여기서 검증하면 글 내용 불러와야 하니까 이전에 확인하고 가져오기로.
2. slug 중복 확인 후, 있는 거면 -UUID(6)로 slug 저장하는 로직은 어디에 둘지 결정 필요.
 */

/**
 * 게시글 관리 설정입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostSetting extends BaseEntity {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	/**
	 * 글 제목
	 */
	@NotNull
	@Size(max = 100)
	@Column(length = 100)
	private String title;

	/**
	 * 미리 보기 등에서 활용할 글에 대한 짧은 소개
	 */
	@NotNull
	@Size(max = 150)
	@Column(length = 150)
	private String brieflyIntroduce;

	/**
	 * 공개, 비공개 설정
	 * @see PostPublicStatus
	 */
	@NotNull
	@Column(columnDefinition = "tinyint(1) unsigned default 0")
	private int isPublic;

	/*
	 * 글의 고유 slug.
	 * 한글, 영어 소문자, 숫자, - 허용.
	 * 작성하지 않으면, 기본으로 제목으로 시도.
	 */
	@NotNull
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-]*$")
	@Size(max = 110)
	@Column(unique = true, length = 110)
	private String slug;

	/**
	 * 게시글 관리 환경을 설정합니다.
	 *
	 * @param post 관리할 게시글
	 * @param title 게시글 제목
	 * @param brieflyIntroduce 게시글 간단 소개
	 * @param postPublicStatus 공개/비공개
	 * @param slug uri에 사용하는 slug
	 * @return 설정한 게시글 환경
	 */
	public static PostSetting setPostEnvironment(
		Post post,
		String title,
		String brieflyIntroduce,
		PostPublicStatus postPublicStatus,
		String slug
	) {
		PostSetting postSetting = new PostSetting();

		postSetting.post = post;
		postSetting.title = title;
		postSetting.brieflyIntroduce = brieflyIntroduce;
		postSetting.isPublic = postPublicStatus.getValueInDB();
		postSetting.slug = slug;

		return postSetting;
	}
}
