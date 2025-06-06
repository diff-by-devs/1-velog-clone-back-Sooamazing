package diffbydevs.velog_clone.global.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

/*
TODO
 1. CRUD 구현 이후, 테이블이 이미 만들어진 후에도 Column의 nullable, updatable이 필요한지 확인하기.
 2. jpa 말고, 하이버네이트 구현체인 createtimestamp 사용하는 경우와 영속성 등 차이 비교.
 */

/**
 * {@link EntityListeners}를 사용해 생성일시, 수정일시를 자동으로 관리합니다.
 * <p>
 *     Entity에서 상속받기만 하면 자동으로 사용 가능합니다.
 *    {@link MappedSuperclass}를 이용해 별개의 entity(테이블)는 생성하지 않습니다.
 * </p>
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Column(nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
