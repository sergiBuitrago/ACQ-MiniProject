package practica01;

import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Classe venda la qual representa una venda a un client
 * @author Nestor Torres
 */
public class Sale {
    private Date dateTime;
    private Vector lineItem=new Vector();
    private boolean isFinished=false;
    private int total;
    private Payment cashPayment;

    /**
     * Constructor d'un parametre que permet iniciar una venda amb una data concreta
     * @param dateTime_p Parametre que indica la data
     */
    public Sale(Date dateTime_p){
        this.dateTime=dateTime_p;
    }

    /**
     * Mètode accessor el qual retorna el total que s'ha de tornar al client després d'haver pagat la compra
     * @return Retorna un float amb el total que s'ha de tornar al client
     */
    public float getAmount(){
        return cashPayment.getAmount() -total();
    }

    /**
     * Mètode accessor el qual retorna la data de la venda
     * @return Retorna la data de la venda en format Date
     */
    public Date getDate(){
        return this.dateTime;
    }

    /**
     * Mètode que finalitza la venda, posar a cert la variable booleana isFinished
     */
    public void finish() {
        isFinished=true;
    }

    /**
     * Mètode que permet saber si una venda esta finalitzada
     * @return Retorna el valor de la variable booleana isFinished
     */
    public boolean isFinished(){
        return isFinished;
    }

    /**
     * Mètode que crea una nova linia de venda de producte per a una compra especifica i l'afegeix al vector de vendes realitzades
     * @param spec Indica la descripció del producte que es vol comprar
     * @param quantity Indica la quantitat que es vol d'aquell producte
     */
    public void createProductLine(ProductDescription spec,int quantity){
        lineItem.addElement(new SalesLineItem(spec,quantity));
    }

    /**
     * Mètode que retorna el total de diners acumulats de la compra
     * @return Retorna un float amb el total de diners de la compra
     */
    public float total(){
        float total=0;
        Enumeration e=lineItem.elements();
        while(e.hasMoreElements()){
            total+=((SalesLineItem)e.nextElement()).subtotal();
        }
        return total;
    }

    /**
     * Mètode que realitza el pagament del total de la compra
     * @param effectiveOffered Indica els diners oferits pel client per a pagar la compra
     */
    public void makePayment(float effectiveOffered){
        cashPayment=new Payment(effectiveOffered);
    }
}
