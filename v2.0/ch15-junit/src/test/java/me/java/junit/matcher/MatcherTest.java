package me.java.junit.matcher;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
//import static org.junit.Assert.assertThat; // deprecated
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.hamcrest.core.CombinableMatcher.either;
import static org.hamcrest.core.Every.everyItem;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/* assertThat(a, Matcher<?> matcher): a가 특정 상황을 만족하는지 확인
* 	matcher
* 		- is: a, is(b): 가독성을 높이기 위한 syntatic sugar
* 		- is(equalTo()): 내용물 같은지 확인
* 		- is(sameInstance()): 주소값 같은지 확인
* 		- isA: a가 A 클래스인지 확인
* 		- allOf: 특정 상황이 모두 만족해야만 true
* 		- anyOf: 특정 상황 중 하나만 만족해도 true
* 		- both: a, both((matcher).and(matcher))
* 		- either: a, either((matcher).or(matcher))
* 		- describedAs: matcher 내부의 메시지 변경해서 출력
* 		- everyItem: 배열이나 리스트를 순회하면서 matcher 실행
* 		- not: matcher의 결과값 반전
* 		- nullValue: null 값인지 확인
* 		- notNullValue: null 값이 아닌지 확인
* 		- containsString: 해당 String이 포함되는지 확인
* 		- startsWith, endsWith: 해당 String의 시작, 끝 확인
* */
public class MatcherTest {
	@Test
	public void isTest() {
		String str1 = new String("abc");
		String str2 = new String("abc");
		assertThat(str1, is(str2)); // 권장 X (가독성 높여주기 위한 방법)
		assertThat(str1, is(equalTo(str2))); // 내용물 같은지 확인
		assertEquals(str1, str2); // 내용물 같은지 확인

		//assertThat(str1, is(equalTo(str2)));
		//assertThat(str1, not(is(str2)));
		//assertThat(str1, is(equalTo(str2)));
		//assertThat(str1, is(instanceOf(str2.getClass())));
	}

	@Test
	public void equalToTest() {
		String str1 = new String("abc");
		String str2 = new String("abc");
		assertThat(str1, not(sameInstance(str2))); // 주소값 같은지 확인
		assertNotSame(str1, str2); // 주소값 같은지 확인
	}

	@Test
	public void nullTest() {
		String str3 = null;
		assertThat(str3, is(nullValue()));
		//assertThat(str3, is(notNullValue()));
	}

	@Test
	public void allOfTest() {
		String str1 = new String("abc");
		//String str2 = new String("abc");
		// "abc"
		assertThat(str1, allOf(startsWith("a"), containsString("c")));

		assertThat(str1, both(containsString("a")).and(containsString("b")));
		assertThat(str1, either(containsString("a")).or(containsString("b")));

	}

	@Test
	public void anyOfTest() {
		String str1 = new String("abc");
		String str2 = new String("abc");
		// "abc"
		assertThat(str1, anyOf(startsWith("d"), containsString("c")));
	}

	@Test
	public void everyItemTest() {
		List<String> list = Arrays.asList("abc", "abd", "abe");
		assertThat(list, everyItem(startsWith("a")));
	}

}
