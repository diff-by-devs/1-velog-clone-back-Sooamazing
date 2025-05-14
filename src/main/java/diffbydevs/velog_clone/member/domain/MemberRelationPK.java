package diffbydevs.velog_clone.member.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
공부
1. package-private으로 두고 싶어서 PACKAGE, 그냥 class로 지정.
module과 package level은 동일하다고 함.
2. (궁금) Serialize는 왜 구현해야 할까?
 */

/**
 * 회원 간 팔로우 관계의 복합 PK입니다.
 * package-private 설정해 해당 패키지 외부에서 PK에 접근할 수 없습니다.
 */

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
class MemberRelationPK implements Serializable {

	@JoinColumn(name = "follower_id")
	private Integer followerId;

	@JoinColumn(name = "following_id")
	private Integer followingId;

}
