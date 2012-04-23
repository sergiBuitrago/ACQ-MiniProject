/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica01;

import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author AnthraX-PC
 */
public class TPVTest extends TestCase {
    
    public TPVTest(String testName) {
        super(testName);
    }

    public void testCompleteSale() {
        System.out.println("completeSale");
        Sale s=new Sale(new Date());
        s.finish();
        assertTrue(s.isFinished());
    }

    public void testProductIntroduction() {
        System.out.println("productIntroduction");
        int cup = 2;
        int quantity = 1;
        ProductCatalog pc=new ProductCatalog();
        ProductDescription pd=new ProductDescription();
        pc.readProducts();
        pd=(ProductDescription)pc.getProducts().get(cup);
        pd.setStock(0);
        TPV instance = new TPV(pc);
        assertFalse(instance.productIntroduction(cup, quantity));
        cup=6;
        assertFalse(instance.productIntroduction(cup, quantity));
        cup=2;
        pd.setStock(6);
        assertTrue(instance.productIntroduction(cup, quantity));
    }

    public void testTotal() {
        System.out.println("total");
        float expResult=new Float(5.06);
        ProductCatalog pc=new ProductCatalog();
        pc.readProducts();
        TPV instance = new TPV(pc);
        instance.productIntroduction(1, 2);
        assertEquals(instance.total(), expResult);
    }

}
