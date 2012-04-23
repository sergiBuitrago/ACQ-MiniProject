package practica01;

/**
 * Classe que representa a la tenda on es realitza la venda
 * @author Nestor Torres
 */
public class Store {
    /*private String name;
    private String address;*/
    private ProductCatalog productCatalog=new ProductCatalog();
    private TPV tpv=new TPV(productCatalog);


    /**
     * Mètode accessor el qual retorna un objecte del TPV
     * @return Retorna l'objecte del TPV
     */
    public TPV getTPV(){
        return tpv;
    }
}
