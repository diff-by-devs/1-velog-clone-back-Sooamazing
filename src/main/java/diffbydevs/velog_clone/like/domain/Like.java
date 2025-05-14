package diffbydevs.velog_clone.like.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyCreatedAt;
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
# 공부
likes는 mysql 예약어라 테이블 이름이 따로 필요함.
 */
/**
 * 회원이 게시글에 좋아요하는 기록입니다.
 */
@Getter
@Entity
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like extends BaseEntityOnlyCreatedAt {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	/**
	 * 게시글에 좋아요를 누릅니다.
	 * <p>
	 *     본인 게시글에도 좋아요를 누를 수 있습니다.
	 * </p>
	 *
	 * @param member 좋아요 누른 회원
	 * @param post 좋아요할 게시글 메타 데이터
	 * @return 좋아요한 날짜를 포함한 정보
	 */
	public static Like likePost(Member member, Post post) {
		Like like = new Like();

		like.member = member;
		like.post = post;

		return like;
	}
}
