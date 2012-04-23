package practica01;

/**
 * Classe que representa al llindar per cada producte
 * @author Nestor Torres
 */
public class Threshold {
    private int limit;

    /**
     * Constructor en blanc
     */
    public Threshold() {}

    /**
     * Constructor d'un parametre el qual permet inicialitzar aquesta clase amb un llindar
     * @param limit_p
     */
    public Threshold(int limit_p){
        limit=limit_p;
    }

   /**
     * Mètode accessor el qual retorna el llindar per un producte
     * @return Retorna un enter amb el llindar definit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Mètode accessor que permet canviar el llindar d'un producte
     * @param limit Parametre que indica el nou llindar per definir
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }



}
