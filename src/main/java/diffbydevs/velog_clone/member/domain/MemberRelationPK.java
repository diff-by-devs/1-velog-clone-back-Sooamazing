package diffbydevs.velog_clone.member.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "createFollowPK")
public class MemberRelationPK implements Serializable {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "follower_id")
	private Member follower;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "following_id")
	private Member following;
}
