package JUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderService2Test {
	private OrderService2 orderService2;
	
	@BeforeEach //initialize orderservice2 before each test case
	public void setUp() {
		orderService2 = new OrderService2();
	}
	
	//Valid Price Cal Test
	@Test
	public void TestCalPrice_ValidInputs() {
		double price = 100.00;
		int quantity = 2;
		double expectedPrice = price*quantity*(1-orderService2.getDiscount());
		assertEquals(expectedPrice, orderService2.calPrice(price,  quantity)); //
	}
	
		@Test
		void TestSuccessfullyPlaced() {
			boolean result = orderService2.placeOrder(5);
			assertTrue(result, "Order should be placed successfully");
			assertEquals(5, orderService2.getStock(), "Stock should be reduced by the ordered quantity");
		}

		@Test
		public void TestCalPrice_invalidquant() {
			double price = 100;
			int quantity = 0;
				assertEquals(0,orderService2.calPrice(price,quantity));
		}
		@Test
			public void TestCalPrice_invalidInputs() {
				IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->{
					orderService2.calPrice(-100.00, 2);
				});
			}
	 
		@Test
		void TestSuccessfullyPlaced1() {
			boolean result = orderService2.placeOrder(5);
			assertTrue(result, "Order should be placed successfully");
			assertEquals(5, orderService2.getStock(), "Stock should be reduced by the ordered quantity");
		}
		//edge case -> quantity exactly same as stock
		 @Test
			 void TestPlaceOrderExactStock() {
			        boolean result = orderService2.placeOrder(10);
			        assertTrue(result, "Order should be placed successfully");
			        assertEquals(0, orderService2.getStock(), "Stock should be 0 after ordering the exact stock amount");
			    }

			//edge case -> order just below stock limit i.e. 10
		 @Test
			 void TestPlaceOrderBelowStockLimit() {
			        boolean result = orderService2.placeOrder(9);
			        assertTrue(result, "Order should be placed successfully");
			        assertEquals(1, orderService2.getStock(), "Stock should be reduced to 1 after ordering 9 out of 10");
			    }
		// Exceptional Cases
			// place order beyond stock
			// set stock -> -ve stock
			// set discount -> (0-1), 1.5 ->
			// negative price
			// negative quantity
		 @Test
		 void TestPlaceOrderbeyoundStock() {
			    int stock = 15;
			    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.placeOrder(stock);
		        });
		    }
		 @Test
		 void TestNegativestock() {
			 int stock = -5;
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.setStock(stock);
		        });
		 }
		 @Test
		 void Testinvaliddiscount() {
			 double discount = 1.5;
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.setDiscount(discount);
		        });
		 }
		 @Test
		 void TestNegativeprice() {
			 int quantity = 4;
			 double price = -50;
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.calPrice(price,quantity);
		        });
		 }
		 @Test
		 void TestNegativequantity() {
			 int quantity = -4;
			 double price = 50;
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.calPrice(price,quantity);
		        });
		 }
		  
		 @ParameterizedTest //same test , diff set of data
		 @ValueSource(ints = {1,4,7,10})
			void testdiffquant(int number) { 
			 assertTrue(orderService2.placeOrder(number));
			}
		 @ParameterizedTest //same test , diff set of data
		 @ValueSource(ints = {11,13,15})
		 void testboundcondition(int number) { 
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.placeOrder(number);
		        });
			}
		 @ParameterizedTest //same test , diff set of data
		 @ValueSource(doubles = {-0.5,1.5,2.0})
		 void testdiscountcondition(double number) { 
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.setDiscount(number);
		        });
		 }
		 @ParameterizedTest //same test , diff set of data
		 @ValueSource(ints = {-5,-10,-15})
		 void teststockcondition(int number) {
			 IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
		            orderService2.placeOrder(number);
		        });
		 }
		 @ParameterizedTest
		 @CsvSource({
			    "100.0, -5",
			    "-100.0, 5",
			    "-50.0, -2"
			})
			void testNegativePriceAndQuantity(double price, int quantity) {
			    IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
			        orderService2.calPrice(price, quantity);
			    });
		 }
		 
    //test case -> 0 quantity
	
	//successfully place order -> assertTrue,
	
	//positive cases
	
	//negative test cases
	
		 static Stream<Integer> validStockValues(){
			 return Stream.of(0,10,20,30);
			 
		 }
		 
		 @ParameterizedTest
		 @MethodSource("validStockValues")
		 public void testsValidStockValues(int stock) {
			 orderService2.setStock(stock);
			 assertEquals(stock, orderService2.getStock());
		 }
		 
		 static  Stream <Arguments>testValidInputs() {
			 return Stream.of(
					 //Arguments.of(100,2,180)
					 org.junit.jupiter.params.provider.Arguments.of(100.00,2,180.0),
					 org.junit.jupiter.params.provider.Arguments.of(100.00,3,270.0),
					 org.junit.jupiter.params.provider.Arguments.of(100.00,4,360.0)

					 );
					 
				 
			 }
		 @ParameterizedTest
		 @MethodSource("testValidInputs")
		 //method def
		 //modify parameterized test -> with method source
		 void testCalPrice_Parameterized(double price, int quantity, double expected) {
		        assertEquals(expected, orderService2.calPrice(price, quantity));
		 }
		 @ParameterizedTest
			@MethodSource("invalidDiscountValues")
			void testInvalidDiscounts(double number) {
			    assertThrows(IllegalArgumentException.class, () -> {
			        orderService2.setDiscount(number);
			    });
			}
			static Stream<Integer> boundaryOrderValues() {
			    return Stream.of(11, 13, 15);
			}
			static Stream<Double> invalidDiscountValues() {
			    return Stream.of(-0.5, 1.5, 2.0);
			}
			@ParameterizedTest
			@MethodSource("negativePriceAndQuantity")
			void testNegPriceNegQuan(double price, int quantity) {
			    assertThrows(IllegalArgumentException.class, () -> {
			        orderService2.calPrice(price, quantity);
			    });
			}
			static Stream<Arguments> negativePriceAndQuantity() {
			    return Stream.of(
			        Arguments.of(100.0, -5),
			        Arguments.of(-50.0, -2)
			    );
			}

}
