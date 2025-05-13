package diffbydevs.velog_clone.member.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyCreatedAt;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
public class MemberRelationship extends BaseEntityOnlyCreatedAt {

	@EmbeddedId
	private MemberRelationPK id;

	/**
	 * 회원이 다른 회원을 팔로우합니다.
	 *
	 * @param follower 팔로우 요청한 회원
	 * @param following 팔로우 대상 회원
	 * @return 팔로우 맺어진 회원들
	 */
	public static MemberRelationship follow(Member follower, Member following) {
		MemberRelationship memberRelationship = new MemberRelationship();

		memberRelationship.id = MemberRelationPK.follow(follower, following);

		return memberRelationship;
	}
}
