package diffbydevs.velog_clone.tag.domain;

import diffbydevs.velog_clone.global.domain.BaseEntityOnlyCreatedAt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
TODO
1. unique 로직 어디서 어떻게 관리할 건지 고민하기. Set 자료구조?
2. 모든 글에서 떨어져 나온 태그 관리? 배치로 Delete query?
3. 태그의 이름과 slug가 다른 경우는 언제가 있을까?
 */

/**
 * 게시글에 추가된 적 있는 태그입니다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag extends BaseEntityOnlyCreatedAt {

	@Id
	@Column(columnDefinition = "int unsigned")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 고유한 태그 이름.
	 * 영어 소문자, 숫자, 한글, -만 허용.
	 */
	@NotNull
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-]*$")
	@Size(max = 100)
	@Column(unique = true, length = 100)
	private String name;

	/*
	 * 태그의 고유 slug.
	 * 한글, 영어 소문자, 숫자, - 허용.
	 * 기본적으로는 태그의 이름과 같음.
	 */
	@NotNull
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-]*$")
	@Size(max = 100)
	@Column(unique = true, length = 100)
	private String slug;

	/**
	 * 새로운 태그를 생성합니다.
	 * 태그 이름이 slug가 됩니다.
	 * 같은 slug가 있다면, 뒤에 -hash(6)를 더합니다.
	 *
	 * @param tagName 태그 이름
	 * @return 생성된 태그
	 */
	public static Tag createTag(String tagName) {
		Tag tag = new Tag();

		tag.name = tagName;
		tag.slug = tagName;

		return tag;
	}
}
