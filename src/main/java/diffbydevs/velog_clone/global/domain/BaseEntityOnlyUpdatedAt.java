package diffbydevs.velog_clone.global.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 수정일시를 자동으로 관리해 줍니다.
 * <p>
 * 필요한 Entity에서 상속받기만 하면 자동으로 사용 가능합니다.
 * {@link MappedSuperclass}를 이용해 entity(테이블)이 따로 생성되지 않습니다.
 * </p>
 */
@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityOnlyUpdatedAt {

	@Column(nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;

}
