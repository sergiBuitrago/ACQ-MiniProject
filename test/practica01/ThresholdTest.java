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
public class ThresholdTest extends TestCase {
    
    public ThresholdTest(String testName) {
        super(testName);
    }

    public void testGetLimit() {
        System.out.println("getLimit");
        Threshold instance = new Threshold();
        int expResult = 5;
        instance.setLimit(expResult);
        int result = instance.getLimit();
        assertEquals(expResult, result);
    }

    public void testSetLimit() {
        System.out.println("setLimit");
        int limit = 5;
        Threshold instance = new Threshold();
        instance.setLimit(limit);
        assertEquals(instance.getLimit(), limit);
    }

}
