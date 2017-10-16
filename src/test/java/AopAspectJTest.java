//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import lab.aop.AopLog;
import lab.model.ApuBar;
import lab.model.Bar;
import lab.model.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context_aop.xml")
public class AopAspectJTest {

	@Autowired
    private Bar bar;
    
	@Autowired
    private Customer customer;

    @BeforeEach
    public void setUp() throws Exception {
    	
        bar.sellSquishee(customer);
    }

    @Test
    public void testBeforeAdvice() {
        assert(AopLog.getStringValue()).contains("Hello");
        assert(AopLog.getStringValue()).contains("How are you doing?");

//        assertTrue("Before advice is not good enought...", AopLog.getStringValue().contains("Hello"));
//        assertTrue("Before advice is not good enought...", AopLog.getStringValue().contains("How are you doing?"));
        System.out.println(AopLog.getStringValue());
    }

//    @Test
//    public void testAfterAdvice() {
//        System.out.println(AopLog.getStringValue());
//        assertTrue("After advice is not good enought...", AopLog.getStringValue().contains("Good Bye!"));
//    }
//
//    @Test
//    public void testAfterReturningAdvice() {
//        assertTrue("Customer is broken", AopLog.getStringValue().contains("Good Enough?"));
//        System.out.println(AopLog.getStringValue());
//    }
//
    @Test
    public void testAroundAdvice() {
        assert (AopLog.getStringValue()).contains("Hi!");
        assert (AopLog.getStringValue()).contains("See you!");
        //assert (AopLog.getStringValue()).contains("See you! (from sayPoliteWordsAndSellAfterSellSquishee)");
//        assertTrue("Around advice is not good enought...", AopLog.getStringValue().contains("Hi!"));
//        assertTrue("Around advice is not good enought...", AopLog.getStringValue().contains("See you!"));
        System.out.println(AopLog.getStringValue());
    }
//
//    @Test
//    public void testAllAdvices() {
//        assertFalse("barObject instanceof ApuBar", bar instanceof ApuBar);
//    }
}