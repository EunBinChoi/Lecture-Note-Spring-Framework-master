package me.java.junit.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * assertEquals(a, b): 객체 a와 b의 값이 같은지 확인
 * assertSame(a, b): 객체 a와 b가 같은 객체인지 확인
 * assertNotSame(a, b): 객체 a와 b가 다른 객체인지 확인
 * assertArrayEquals(a, b): 배열 a와 b가 같은지 확인
 * assertTrue(a): a가 참인지 확인
 * assertFalse(a): a가 거짓인지 확인
 * assertNotNull(a): a가 null이 아님을 확인
 * assertNull(a): a가 null인지 확인
 * fail(msg): 무조건 실패 (msg: 실패 메시지)
 * assertThat(a, Matcher<?> matcher): a가 특정 상황을 만족하는지 확인
 * 	matcher
 * 		- is: a, is(b): a와 b가 같은 객체인지 확인
 * 		- is(equalTo()): a와 b가 같은 객체인지 확인
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
public class CalculatorTest {

	private static Calculator calculator;

	@BeforeClass // 테스트 클래스 메모리 로드 전에 딱 한번만 호출
	public static void calculator() {
		CalculatorTest.calculator = new Calculator();
	}

//	@Before // 테스트 함수마다 시작 전에 호출
//	public void calculator() {
//		this.calculator = new Calculator();
//	}

	// @Before -> @Test -> @After
	@Test(timeout = 5000) // 5초 안에
	public void addTest() {
		int res = calculator.add(3, 5);
		assertEquals(res, 8); // res의 결과가 10이랑 같은지 확인
	}

	// @Before -> @Test -> @After
	@Test(timeout = 5000, expected = RuntimeException.class)
	// 5초 안에 RuntimeException이 발생하는지 확인
	public void divTest() {
		int res = calculator.div(3, 0);
	}

//	@After // 테스트 함수 끝날 때마다 호출
//	public void close() {
//		calculator = null;
//	}

	@AfterClass // 테스트 클래스가 메모리 로드 후에 딱 한번만 호출
	public static void close() {
		CalculatorTest.calculator = null;
	}
}
