package practica01;

/**
 * Classe ProductDescription la qual representa a la descripció i detalls d'un producte determinat
 * @author Nestor Torres
 */
public class ProductDescription {
    private int cup;
    private String description;
    private float price;
    private Stock stock=new Stock();
    private Threshold threshold=new Threshold();


    /**
     * Constructor en blanc
     */

    public ProductDescription(){}

    /**
     * Constructor de un parametre el qual permet inicialitzar un objecte només amb l'identificador de producte
     * @param cup_p Ens indica el identificador per a inicialitzar la classe
     */
    public ProductDescription(int cup_p){
        this.cup=cup_p;
    }

    /**
     * Constructor de tres parametres el qual ens permet crear un producte amb tots els seus camps
     * @param cup_p Indica l'identificador del producte
     * @param description_p Indica la descripció del producte
     * @param price_p Indica el preu del producte
     */
    public ProductDescription(int cup_p, String description_p, float price_p){
        this.cup=cup_p;
        this.description=description_p;
        this.price=price_p;
    }
    
    /**
     * Mètode accessor el qual retorna l'identificador del producte
     * @return Retorna un enter amb l'identificador de producte
     */
    public int getCup() {
        return cup;
    }

    /**
     * Mètode accessor que permet canviar l'identificador de producte
     * @param cup Parametre que indica el nou identificador que es canviarà
     */
    public void setCup(int cup) {
        this.cup = cup;
    }

    /**
     * Mètode accessor el qual retorna la descripció del producte
     * @return Retorna un String amb la descripció del producte
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mètode accessor que permet canviar la descripció del producte
     * @param description Parametre que indica la nova descripció
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Mètode accessor el qual retorna el preu del producte
     * @return Retorna un float amb el preu del producte
     */
    public float getPrice() {
        return price;
    }

    /**
     * Mètode accessor que permet canviar el preu del producte
     * @param price Parametre que indica el nou preu del producte
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Mètode accessor que permet canviar el numero de productes en estoc
     * @param stock_p Parametre que indica el nou estoc del producte
     */
    public void setStock(int stock_p){
        stock.setNumProducts(stock_p);
    }

    /**
     * Mètode accessor que permet canviar el llindar del producte
     * @param limit_p Parametre que indica el nou llinar del producte
     */
    public void setThreshold(int limit_p){
        threshold.setLimit(limit_p);
    }

     /**
     * Mètode accessor el qual retorna l'estoc del producte
     * @return Retorna un enter amb l'estoc del producte
     */
    public int getStock(){
        return stock.getNumProducts();
    }

     /**
     * Mètode accessor el qual retorna el llindar del producte
     * @return Retorna un enter amb el llindar del producte
     */
    public int getThreshold(){
        return threshold.getLimit();
    }

    /**
     * Mètode el qual serveix per saber si el producte esta igual o per sobre del llindar o per sota
     * @return Retorna cert si esta per sobre del llindar i fals si esta per sota
     */
    public boolean checkStock(){
        if(stock.getNumProducts()>=threshold.getLimit()){
            return true;
        }else{
            return false;
        }
    }
}
