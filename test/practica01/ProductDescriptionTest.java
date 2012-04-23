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
public class ProductDescriptionTest extends TestCase {
    
    public ProductDescriptionTest(String testName) {
        super(testName);
    }

    public void testCheckStock() {
        System.out.println("checkStock");
        ProductDescription prod=new ProductDescription(3);
        prod.setStock(20);
        prod.setThreshold(5);
        assertTrue(prod.checkStock());
        prod.setStock(3);
        prod.setThreshold(5);
        assertFalse(prod.checkStock());
    }

}
