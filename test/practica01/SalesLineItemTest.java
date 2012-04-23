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
public class SalesLineItemTest extends TestCase {
    
    public SalesLineItemTest(String testName) {
        super(testName);
    }

    public void testSubtotal() {
        System.out.println("subtotal");
        float expResult=9;
        ProductDescription pd=new ProductDescription(3);
        pd.setPrice(3);
        int quantity=3;
        SalesLineItem sli=new SalesLineItem(pd, quantity);
        assertEquals(sli.subtotal(), expResult);
    }

}
