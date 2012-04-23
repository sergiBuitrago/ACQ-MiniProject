/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica01;

import java.util.Hashtable;
import junit.framework.TestCase;

/**
 *
 * @author AnthraX-PC
 */
public class ProductCatalogTest extends TestCase {
    
    public ProductCatalogTest(String testName) {
        super(testName);
    }

    public void testGetQuantityProducts() {
        System.out.println("getQuantityProducts");
        ProductCatalog pc = new ProductCatalog();
        int expResult = 4;
        pc.readProducts();
        assertEquals(pc.getQuantityProducts(), expResult);
        
    }
}
