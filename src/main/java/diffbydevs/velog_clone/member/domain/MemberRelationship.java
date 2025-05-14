package diffbydevs.velog_clone.member.domain;

import org.springframework.data.domain.Persistable;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyCreatedAt;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
1. 복합 PK는 인덱스에 좋은 영향을 못 준다. 특히 하나씩 조회할 때. -> 비교해 보기.
2. IdClass, Embeddable 성능, 사용 방식 등 비교
3. TODO 본인이 본인을 팔로우할 수 없음.
4. EmbeddedId 여기에도 NotNull이 필요할지 의문. 우선, pk는 Notnull이고, 생성할 때 무조건 PK를 만들고, 어플리케이션에서는 거기에서도 검사하기 때문에 따로 걸지 않았음.
 */

/**
 * 회원끼리의 팔로우 관계입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRelationship extends BaseEntityOnlyCreatedAt implements Persistable<MemberRelationPK> {

	@EmbeddedId
	private MemberRelationPK id;

	@MapsId("followerId")
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "follower_id")
	private Member follower;

	@MapsId("followingId")
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "following_id")
	private Member following;

	/**
	 * 회원이 다른 회원을 팔로우합니다.
	 *
	 * @param follower 팔로우 요청한 회원
	 * @param following 팔로우 대상 회원
	 * @return 팔로우 맺어진 회원들
	 */
	public static MemberRelationship follow(Member follower, Member following) {
		MemberRelationship memberRelationship = new MemberRelationship();

		memberRelationship.follower = follower;
		memberRelationship.following = following;

		return memberRelationship;
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
