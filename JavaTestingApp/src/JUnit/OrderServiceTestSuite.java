package JUnit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
	OrderServiceTest.class,
	OrderService2Test.class,
	OrderService2MultiThreadTest.class,
})

public class OrderServiceTestSuite {

}
