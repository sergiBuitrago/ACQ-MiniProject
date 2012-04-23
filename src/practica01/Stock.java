package practica01;

/**
 * Classe que representa el estoc de productes que queda en el magatzem de cada producte
 * @author Nestor Torres
 */
public class Stock {
    private int numProducts;


    /**
     * Constructor en blanc
     */
    public Stock(){}

    /**
     * Constructor d'un parametre en el que s'inicialitza la classe amb el numero de productes que hi ha en estoc
     * @param numProducts_p Numero de productes en estoc
     */
    public Stock(int numProducts_p){
        numProducts=numProducts_p;
    }

   /**
     * Mètode accessor el qual retorna el numero de productes en estoc
     * @return Retorna un enter amb el numero de productes en estoc
     */
    public int getNumProducts() {
        return numProducts;
    }

    /**
     * Mètode accessor que permet canviar el numero d'existencies en estoc d'un producte
     * @param numProducts Parametre que indica el nou estoc d'aquell producte
     */
    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

}
