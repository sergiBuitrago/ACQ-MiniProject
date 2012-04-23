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
public class StoreTest extends TestCase {
    
    public StoreTest(String testName) {
        super(testName);
    }

    public void testGetTPV() {
        System.out.println("getTPV");
        Store instance = new Store();
        TPV expResult = instance.getTPV();
        TPV result = instance.getTPV();
        assertEquals(expResult, result);
    }

}
