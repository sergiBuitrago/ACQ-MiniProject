package practica01;

/**
 * Classe Payment la qual realitzara el pagament al finalitzar la compra
 * @author Nestor Torres
 */
public class Payment {
    private float amountTendered;

    /**
     * Constructor Payment(float)
     * @param amountTentered_p Parametre que conte els diners amb efectiu pagats per la compra
     */
    public Payment(float amountTentered_p){
        this.amountTendered=amountTentered_p;
    }

    /**
     * Mètode accessor el qual retorna l'efectiu pagat
     * @return Retorna un float amb l'efectiu pagat
     */
    public float getAmount(){
        return this.amountTendered;
    }
}
