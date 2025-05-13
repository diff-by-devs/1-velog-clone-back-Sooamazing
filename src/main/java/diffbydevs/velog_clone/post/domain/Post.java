package diffbydevs.velog_clone.post.domain;

import java.time.LocalDateTime;

import diffbydevs.velog_clone.blog.domain.Blog;
import diffbydevs.velog_clone.member.domain.Member;
import diffbydevs.velog_clone.series.domain.Series;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
공부
TODO
1. 이거 null인 것도 내가… 생성자에서 꼭 설정을 해 줘야 했나..?
2. publishedAt -> not null query 확인 필요
3. publishedAt -> 한번 저장하면 이후로 바꿀 수 없게 할 수 있는 방법은 없나?
 */

/**
 * 게시글의 메타 데이터입니다.
 * <p>
 *     외부와 통신 시 해당 id로 소통합니다.
 * </p>
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	private Blog blog;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Member author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "series_id")
	private Series series;

	/**
	 * 발행 상태
	 * @see PostPublishedStatus
	 */
	@Column(columnDefinition = "tinyint(1) unsigned default 0")
	private int publishedStatus;

	/**
	 * 발행일. 임시 저장이면 발행일은 없고, 발행 이후엔 변경 불가함.
	 */
	private LocalDateTime publishedAt;

	/**
	 * 게시글을 시리즈에 포함해 임시 저장 혹은 발행합니다.
	 *
	 * @param blog 글을 작성할 블로그
	 * @param author 글을 작성한 회원
	 * @param series 글이 소속될 시리즈
	 * @param publishedStatus 발행 상태
	 * @return 게시글의 메타 데이터
	 */
	public static Post createPost(
		Blog blog,
		Member author,
		Series series,
		PostPublishedStatus publishedStatus
	) {
		Post post = new Post();

		post.blog = blog;
		post.author = author;
		post.series = series;
		post.publishedStatus = publishedStatus.getValueInDB();

		// 기본값
		post.publishedAt = null;
		if (publishedStatus == PostPublishedStatus.PUBLISHED) {
			post.publishedAt = LocalDateTime.now();
		}

		return post;
	}

	/**
	 * 게시글을 시리즈 없이 임시 저장 혹은 발행한다.
	 *
	 * @param blog 글을 작성할 블로그
	 * @param author 글을 작성한 회원
	 * @param publishedStatus 발행 상태
	 * @return 게시글의 메타 데이터
	 */
	public static Post createPost(
		Blog blog,
		Member author,
		PostPublishedStatus publishedStatus
	) {
		Post post = new Post();

		post.blog = blog;
		post.author = author;
		post.series = null;
		post.publishedStatus = publishedStatus.getValueInDB();
		post.publishedAt = null;

		if (publishedStatus == PostPublishedStatus.PUBLISHED) {
			post.publishedAt = LocalDateTime.now();
		}

		return post;
	}
}
