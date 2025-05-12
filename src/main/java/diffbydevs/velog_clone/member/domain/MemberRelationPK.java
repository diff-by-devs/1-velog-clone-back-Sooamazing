package diffbydevs.velog_clone.member.domain;

import java.io.Serializable;

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
 * 회원 관계에서 복합 PK 생성을 위한 클래스.
 * 해당 패키지 외부에서 PK를 만들 일은 없어야 하기에 package-private으로 package 내에서만 사용 가능합니다.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PACKAGE, staticName = "follow")
@EqualsAndHashCode
class MemberRelationPK implements Serializable {

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "follower_id")
	private Member follower;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "following")
	private Member following;

}
