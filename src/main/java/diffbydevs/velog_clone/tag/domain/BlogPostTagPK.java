package diffbydevs.velog_clone.tag.domain;

import diffbydevs.velog_clone.blog.domain.Blog;
import diffbydevs.velog_clone.post.domain.PostPublishedInfo;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 볼로그 게시글 태그의 복합키를 관리합니다.
 * 해당 패키지 외부에서 PK를 만들 일은 없어야 하기에 package-private으로 package 내에서만 사용 가능합니다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "tagAPost")
@EqualsAndHashCode
class BlogPostTagPK {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tag_id")
	private Tag tag;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	private Blog blog;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_published_info_id")
	private PostPublishedInfo post;
}
