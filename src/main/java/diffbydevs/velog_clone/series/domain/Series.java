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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 블로그 내 글을 모아 볼 수 있는 시리즈.
 * <p>
 *     블로그 주인이 직접 만들고, 수정하고, 삭제할 수 있습니다.
 * </p>
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Series extends BaseEntity {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	private Blog blog;

	/**
	 * 시리즈 이름
	 * <p>
	 *     slug로 사용됨.
	 * </p>
	 */
	@NotNull
	@Size(max = 50)
	@Column(unique = true, length = 50)
	private String name;

	/**
	 * 시리즈를 만듭니다.
	 *
	 * @param blog 시리즈를 만들 블로그
	 * @param name 시리즈 이름
	 * @return 생성된 시리즈
	 */
	public static Series createSeries(Blog blog, String name) {
		Series series = new Series();

		series.blog = blog;
		series.name = name;

		return series;
	}
}
