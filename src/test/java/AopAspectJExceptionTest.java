//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;


import org.junit.jupiter.api.*;
import lab.aop.AopLog;
import lab.model.Bar;
import lab.model.Customer;
import lab.model.CustomerBrokenException;

//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context_aop.xml")

public class AopAspectJExceptionTest {

	@Autowired
	private Bar bar;
    
	@Autowired
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
        
//        customer.setBroke(true);
    }

//    @Test(expected=CustomerBrokenException.class)
//    public void testAfterThrowingAdvice() {
//
//        bar.sellSquishee(customer);
//
//        assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
//        System.out.println(AopLog.getStringValue());
//    }

    @Test
    public void testAfterThrowingAdvice() {

        assertThrows(CustomerBrokenException.class, () -> bar.sellSquishee(customer));

        assertTrue("Customer is not broken ", AopLog.getStringValue().contains("Hmmm..."));
        System.out.println(AopLog.getStringValue());
    }

//    @Test
//    void testAfterThrowingAdvice() {
//
//        String fromSystemOut = TestUtils.fromSystemOut(() ->
//                assertThrows(CustomerBrokenException.class,
//                        () -> bar.sellSquishee(customer)));
//
//        assertTrue("Customer is not broken",
//                fromSystemOut.contains("Hmmm..."));
//    }

}