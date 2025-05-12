package diffbydevs.velog_clone.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/* # 공부용 주석
 * ## JPA Auditing
 * - JPA를 감시하며 엔티티 관련 이벤트를 추적하고 기록함.
 * ## Main이 아니고 config인 이유
 * - 스프링 컨테이너가 필요한 거 할 때 JPA metamodel must not be empty! 오류 발생(<a href="https://tao-tech.tistory.com/14">참고</a>
 * )
 * - DataJpaTest 시 Main을 import하게 되면, 단위 테스트의 의미가 없어짐.
 */

/**
 * JPA 설정
 * <p>
 * 생성일, 수정일 자동 관리 기능 이용 위해 JPA Auditing 설정.
 * 설정을 각각 관리하고, 모아서 확인하기 위해 main이 아니라 config로 관리.
 * </p>
 * @see diffbydevs.velog_clone.global.domain.BaseEntity  AuditingEntityListener 사용한 BaseEntity
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
