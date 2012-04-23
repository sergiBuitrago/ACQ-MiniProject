package practica01;

/**
 * Classe que representa la venda d'un producte determinat
 * @author Nestor Torres
 */
public class SalesLineItem {
    private int quantity;
    private ProductDescription specProd;

    /**
     * Constructor de dos parametres el qual permet crear la linia de venda de producte amb el producte que es ven i la seva quantitat
     * @param specProd_p Objecte amb el producte que es ven
     * @param quantity_p Enter amb la quantitat que es vol d'aquell producte
     */
    public SalesLineItem(ProductDescription specProd_p,int quantity_p){
        this.specProd=specProd_p;
        this.quantity=quantity_p;
        System.out.println(" - - Nou producte - - ");
        System.out.println("Descripció: " + specProd_p.getDescription());
        System.out.println("Preu: " + specProd_p.getPrice());
        System.out.println("Subtotal: " + subtotal());
    }
   
    /**
     * Mètode que retorna el subtotal que costa aquell producte determinat amb la quantitat indicada
     * @return Retorna un float amb el subtotal
     */
    public float subtotal(){
        return quantity * specProd.getPrice();
    }

}
