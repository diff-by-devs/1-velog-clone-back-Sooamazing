package diffbydevs.velog_clone.tag.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 볼로그 게시글 태그의 복합 PK입니다.
 * package-private 설정해 해당 패키지 외부에서 PK에 접근할 수 없습니다.
 */
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
class BlogPostTagPK implements Serializable {

	@JoinColumn(name = "tag_id")
	private Integer tagId;

	@JoinColumn(name = "blog_id")
	private Integer blogId;

	@JoinColumn(name = "post_id")
	private Integer postId;
}
