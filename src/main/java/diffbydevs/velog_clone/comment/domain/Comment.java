package diffbydevs.velog_clone.comment.domain;

import diffbydevs.velog_clone.global.domain.BaseEntity;
import diffbydevs.velog_clone.member.domain.Member;
import diffbydevs.velog_clone.post.domain.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
1. @Nullable은 안 되겠지? validation이 아니던데... 나중에 확인해 보기.
2. Entity 에러는... Entity 생성이 아니고 저장 위해 쿼리 날리 때 나는 거겠지?
3. Level +1 하고, 안 되는지 확인하는 건 어디서 책임을 질까?
4. 게시글, 상위 댓글이 삭제됐는지도 확인해.
5. ParentComment 쿼리 null 맞는지 확인하기
6. (공부) comment는 Mysql 예약어라 테이블 이름이 따로 필요함.
 */

/**
 * 회원이 남긴 댓글입니다.
 */
@Getter
@Entity
@Table(name = "commments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commenter_id")
	private Member commenter;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_comment_id")
	private Comment parentComment;

	/**
	 * 댓글 내용
	 */
	@NotNull
	@Column(columnDefinition = "varchar(1000)")
	private String content;

	/**
	 * 댓글 계층 - {@link CommentLevel#COMMENT 댓글},
	 * {@link CommentLevel#REPLY 대댓글},
	 * {@link CommentLevel#LAST_REPLY 대대댓글}
	 * @see CommentLevel
	 */
	@NotNull
	@Column(columnDefinition = "tinyint(1) unsigned default 0")
	private int level;

	/**
	 * 댓글 삭제 여부
	 * @see CommentActiveStatus
	 */
	@Column(columnDefinition = "tinyint(1) unsigned default 0")
	private int isDeleted;

	/**
	 * (최상위) 댓글을 답니다.
	 *
	 * @param post 댓글 달 게시글
	 * @param commenter 댓글 다는 회원
	 * @param content 댓글 내용
	 * @return 남긴 댓글
	 */
	public static Comment leaveComment(
		Post post,
		Member commenter,
		String content
	) {
		Comment comment = new Comment();

		comment.post = post;
		comment.commenter = commenter;
		comment.content = content;

		// 기본 값 설정
		comment.parentComment = null;
		comment.level = CommentLevel.COMMENT.getValueInDB();
		comment.isDeleted = CommentActiveStatus.ACTIVE.getValueInDB();

		return comment;
	}

	/**
	 * 대댓글, 대대댓글을 답니다.
	 *
	 * @param post 댓글 달 게시글
	 * @param commenter 댓글 다는 회원
	 * @param parentComment 상위 레벨의 댓글
	 * @param content 댓글 내용
	 * @param level 댓글 계층
	 * @return 남긴 댓글
	 */
	public static Comment leaveComment(
		Post post,
		Member commenter,
		Comment parentComment,
		String content,
		CommentLevel level
	) {
		Comment comment = new Comment();

		comment.post = post;
		comment.commenter = commenter;
		comment.parentComment = parentComment;
		comment.content = content;
		comment.level = level.getValueInDB();

		// 기본 값 설정
		comment.isDeleted = CommentActiveStatus.ACTIVE.getValueInDB();

		return comment;
	}
}
