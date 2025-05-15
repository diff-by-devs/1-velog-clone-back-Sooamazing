package diffbydevs.velog_clone.tag.domain;

import org.springframework.data.domain.Persistable;

import diffbydevs.velog_clone.blog.domain.Blog;
import diffbydevs.velog_clone.global.domain.BaseEntityOnlyCreatedAt;
import diffbydevs.velog_clone.post.domain.Post;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
1. Persistable 구현 안 하면 Select 쿼리가 한 번 더 나가는 게 맞나 확인 필요
참고:  https://rachel0115.tistory.com/entry/JPA-%EB%B3%B5%ED%95%A9%ED%82%A4-%EB%A7%A4%ED%95%91%ED%95%98%EA%B8%B0-EmbeddedId-MapsId-isNew
 */
/**
 * 블로그의 게시글 태그입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlogPostTag extends BaseEntityOnlyCreatedAt implements Persistable<BlogPostTagPK> {

	@EmbeddedId
	private BlogPostTagPK id;

	/**
	 * 블로그 게시글에 태그를 추가합니다.
	 *
	 * @param tag 게시글에 추가할 태그
	 * @param blog 게시글을 작성할 블로그
	 * @param post 태그를 추가할 게시글
	 * @return 블로그 게시글에 추가한 태그
	 */
	public static BlogPostTag addTagTo(
		Tag tag, Blog blog, Post post) {
		BlogPostTag blogPostTag = new BlogPostTag();

		blogPostTag.id = BlogPostTagPK.createBlogPostTagPK(tag, blog, post);

		return blogPostTag;
	}

	/**
	 * GeneratedValue를 사용하지 않고 복합키 사용 시, 이미 id 값을 가지고 있어 이미 있던 entity로 판단할 수 있기에 jpa의 새로운 엔티티 판단 전략을 재정의합니다.
	 * @return 새롭다면 true, 있던 거라면 false
	 */
	@Override
	public boolean isNew() {
		return super.getCreatedAt() == null;
	}
}
