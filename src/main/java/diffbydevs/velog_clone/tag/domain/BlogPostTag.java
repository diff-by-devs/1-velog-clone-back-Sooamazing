package diffbydevs.velog_clone.tag.domain;

import diffbydevs.velog_clone.blog.domain.Blog;
import diffbydevs.velog_clone.global.domain.BaseEntityOnlyCreatedAt;
import diffbydevs.velog_clone.post.domain.Post;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 블로그의 게시글 태그를 관리합니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BlogPostTag extends BaseEntityOnlyCreatedAt {

	@EmbeddedId
	private BlogPostTagPK id;

	/**
	 * 블로그 게시글에 태그를 붙입니다.
	 *
	 * @param tag 게시글에 붙일 태그
	 * @param blog 게시글을 작성할 블로그
	 * @param post 태그를 붙일 게시글
	 * @return 블로그 게시글에 붙인 태그
	 */
	public static BlogPostTag tagAPost(Tag tag, Blog blog, Post post) {
		BlogPostTag blogPostTag = new BlogPostTag();

		blogPostTag.id = BlogPostTagPK.tagAPost(tag, blog, post);

		return blogPostTag;
	}

}
