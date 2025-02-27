package JUnit;
 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;
 


class OrderServiceTest {

	OrderService orderService =  new OrderService();

	//calTotal

	@Test

	void testCalTotal(){

		double total = orderService.calPrice(100.00, 2);

		assertEquals(180,total, "Total should be 180 after 10% discount");

		}

 
	 

		@Test

		void test() {

			OrderService os=new OrderService();

			assertEquals(450, os.calPrice(50, 10));

			//fail("Not yet implemented");

		}

		@Test

		void testException()

		{

			OrderService os=new OrderService();

			IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->{

				os.placeOrder(12);

			});

		}

		@Test

		void SufficientStock()

		{

			OrderService os=new OrderService();

			assertTrue(os.placeOrder(5));

		}

		@Test

		void InSufficientStock()

		{

			OrderService os=new OrderService();

			assertTrue(os.placeOrder(2));

		}

		@ParameterizedTest

		@CsvSource({

				"50, 10, 450",

//				"7, 5, 12",

				"10, 10, 90"

//				"8, 9, 17"
 
		})

		void testAddParamcsv(int a, int b, int expected)

		{

			OrderService os=new OrderService();

			assertEquals(expected,os.calPrice(a,b));

		}

		@Test

		void testTimeout()

		{

			OrderService os=new OrderService();

			assertTimeout(java.time.Duration.ofMillis(1000),()->{

				Thread.sleep(500);

				os.calPrice(50, 10);

			});

		}

		@Test

		void test0() {

			OrderService os=new OrderService();

			assertEquals(0, os.calPrice(50, 0));

			//fail("Not yet implemented");

		}
		
		void testPlaceOrderMultithread() throws InterruptedException{
			Thread thread1 = new Thread(() -> orderService.placeOrder(2));
			Thread thread2 = new Thread(() -> orderService.placeOrder(2));

			thread1.start();
			thread2.start();
			
			thread1.join();
			thread2.join();
			
			assertEquals(7, orderService.getStock());
		}
		@BeforeAll
	    public static void beforeAllTests() {
	        System.out.println("Testing Started");
	    }
	 
	    @AfterAll
	    public static void afterAllTests() {
	        System.out.println("All Tests Completed");
	    }
	    @AfterEach
	    public void afterEachTest() {
	        System.out.println("Test Completed");
	    }

	    @Test
	    @Disabled("Test disabled for demonstration purposes")
	    public void testDisabled() {
	        System.out.println("This test is disabled and will not run");
	    }

	}

	//testPlaceOrder()-> placeorder(5)-> succeed

	//stock should reduced to 5


//	@Test

//	void test() {

//		OrderService os=new OrderService();

//		assertEquals(450, os.calPrice(50, 10));

//		//fail("Not yet implemented");

//	}

// 

//	@Test

//	void testException()

//	{

//		OrderService os=new OrderService();

//		IllegalArgumentException exception=assertThrows(IllegalArgumentException.class, ()->{

//			os.placeOrder(12);

//		});
 
	

 