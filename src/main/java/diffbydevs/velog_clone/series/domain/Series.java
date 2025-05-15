package diffbydevs.velog_clone.series.domain;

import diffbydevs.velog_clone.blog.domain.Blog;
import diffbydevs.velog_clone.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 블로그 내 글을 모을 수 있는 시리즈입니다.
 * <p>
 *     블로그 주인이 직접 관리합니다.
 * </p>
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Series extends BaseEntity {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	private Blog blog;

	/**
	 * 시리즈 이름, 고유 slug로 사용.
	 */
	@NotNull
	@Size(max = 50)
	@Column(unique = true, length = 50)
	private String name;

	/**
	 * 새로운 시리즈를 만듭니다.
	 *
	 * @param blog 시리즈가 속할 블로그
	 * @param name 시리즈 이름
	 * @return 새로운 시리즈
	 */
	public static Series createSeries(Blog blog, String name) {
		Series series = new Series();

		series.blog = blog;
		series.name = name;

		return series;
	}
}
