/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica01;

import junit.framework.TestCase;

/**
 *
 * @author AnthraX-PC
 */
public class PaymentTest extends TestCase {
    
    public PaymentTest(String testName) {
        super(testName);
    }

    public void testGetAmount() {
        System.out.println("getAmount");
        float expResult=3;
        Payment p=new Payment(expResult);
        assertEquals(p.getAmount(), expResult);
        
    }

}
