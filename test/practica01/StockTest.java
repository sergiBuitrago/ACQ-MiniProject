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
public class StockTest extends TestCase {
    
    public StockTest(String testName) {
        super(testName);
    }

    public void testGetNumProducts() {
        System.out.println("getNumProducts");
        Stock instance = new Stock();
        instance.setNumProducts(4);
        int expResult = 4;
        int result = instance.getNumProducts();
        assertEquals(expResult, result);
    }

    public void testSetNumProducts() {
        System.out.println("setNumProducts");
        int numProducts = 4;
        Stock instance = new Stock();
        instance.setNumProducts(numProducts);
        assertEquals(instance.getNumProducts(), numProducts);
    }

}
