/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package practica01;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;
import junit.framework.TestCase;

/**
 *
 * @author AnthraX-PC
 */
public class SaleTest extends TestCase {
    
    public SaleTest(String testName) {
        super(testName);
    }


    public void testIsFinished() {
        System.out.println("isFinished");
        Sale sale = new Sale(new Date());
        sale.finish();
        assertTrue(sale.isFinished());
    }

    public void testTotal() {
        System.out.println("total");
        Vector lineItem=new Vector();
        float total=0;
        float expResult=4;
        ProductDescription pd=new ProductDescription(3);
        pd.setPrice(2);
        pd.setDescription("Producte de prova");
        lineItem.addElement(new SalesLineItem(pd,2));
        Enumeration e=lineItem.elements();
        while(e.hasMoreElements()){
            total+=((SalesLineItem)e.nextElement()).subtotal();
        }
        assertEquals(total, expResult);
    }

}
