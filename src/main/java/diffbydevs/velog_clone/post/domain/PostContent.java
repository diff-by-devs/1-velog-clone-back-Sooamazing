package diffbydevs.velog_clone.post.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyUpdatedAt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 내용입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostContent extends BaseEntityOnlyUpdatedAt {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@Lob
	private String content;

	/**
	 * 게시글 내용을 작성합니다.
	 *
	 * @param post 게시글 메타 데이터
	 * @param content 작성할 게시글 내용
	 * @return 작성한 게시글 내용
	 */
	public static PostContent createPostContent(Post post, String content) {
		PostContent postContent = new PostContent();

		postContent.post = post;
		postContent.content = content;

		return postContent;
	}
}
