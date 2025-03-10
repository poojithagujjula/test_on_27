package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;



class CalculatorTest {
	Calculator calc = new Calculator();

	@Test
	void testAdd() {
		Calculator calc = new Calculator();
		calc.add(5, 10); //functionality -. actual result
		//assertion -> match in expected result, actual result
		//assertEquals
		assertEquals(15, calc.add(5, 10)); //test case for add method
	}

	@Test
	void testSub() {
		Calculator calc = new Calculator();
		assertEquals(-5, calc.sub(5,10));
	}
	
	//assertTrue(condition) -> verify that condition is true
	//assertFalse(condition) -> verify that cond is false
	
	@Test
	void testConditionT() {
		Calculator calc = new Calculator();
		assertTrue(calc.add(2,9)>0);
	}
	
	@Test
	void testConditionF() {
		Calculator calc = new Calculator();
		assertFalse(calc.sub(9,8)<0);
	}
	
	@Disabled("This is currently disabled")

	@Test
	void testNull() {
		Calculator calc = new Calculator();
		assertNull(calc.add(null,null));
	}
	
	@Disabled("This is currently disabled")
    @Test
	void testNotNull() {
		Calculator calc = new Calculator();
		assertNotNull(calc.add(2,8));
	}
	
   //Parameterized Tests
   //Scenario -> run the same test with different sets of data
   // similar test cases
   // use @ParameterizedTest, source of arguments ->
	
	@ParameterizedTest
	@ValueSource(ints = {12,15,16,17}) //item1 -> 12+10 -> 22
	void testAddParam(int number) { // number -> from valuesource
		Calculator calc=new Calculator();
		assertEquals(number+10,calc.add(number,10));
	}
	
	
	@ParameterizedTest
	@CsvSource ({
		"5, 7, 12",
		"3, 6, 9",
		"7, 5, 12",
		"8, 9, 17"
	})
	//a b expected
	void testAddParamCsv(int a, int b, int expected) { //csv source
		Calculator calc = new Calculator();
		assertEquals(expected, calc.add(a,b));
		
	} 
	// run for 4 sets -> 
	//few tests -> too long to run -> infinite loop, performance issues,
	//assetTimeout, Junit 4 - Test -> timeout param
	//fail -> longer time
	
	@Test
	void testTimeout() {
		assertTimeout(java.time.Duration.ofMillis(1000),() ->{
			
			//simulate
			Thread.sleep(500);
			calc.add(2,3);
		});
	}
	
	//Disable the test
	//Junit 4 -> @Ignore
	//Junit 5 -> @Disabled 
	
	
	@Test
	void testExceptions() {
		ArithmeticException exception = assertThrows(ArithmeticException.class, () ->{
			calc.div(12, 2);
		});
		assertEquals("/by zero",exception.getMessage());
	}
}
