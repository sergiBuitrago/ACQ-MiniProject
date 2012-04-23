package practica01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe que representa el Terminal Punt de Venda
 * @author Nestor Torres
 */
public class TPV {
    private Calendar calendari = Calendar.getInstance();
    private ProductCatalog productCatalog;
    private Sale sale=new Sale(calendari.getTime());
    private Hashtable vendesEstoc=new Hashtable();

    /**
     * Constructor en blanc
     */
    public TPV(){}

    /**
     * Constructor d'un parametre en el que es passa el cataleg de productes a l'objecte TPV
     * @param prodCatalog Indica el cataleg de productes
     */
    public TPV(ProductCatalog prodCatalog){
        this.productCatalog=prodCatalog;
    }

    /**
     * Mètode per a completar la venda
     */
    public void completeSale(){
        sale.finish();
    }

    /**
     * Mètode per a processar un producte en una venda
     * @param cup Indica l'identificador del producte introduit per l'usuari
     * @param quantity Indica la quantitat del producte desitjada
     * @return Retorna cert si tot ha anat correctament o fals si s'ha produit algun tipus d'error
     */
    public boolean productIntroduction(int cup, int quantity){
        boolean ok=false;
        if(isNewSale()){
            sale=new Sale(calendari.getTime());
        }
        if(productCatalog.getProducts().containsKey(cup)){
            ProductDescription desc=productCatalog.getDescription(cup);
            if(desc.getStock()>0){
                vendesEstoc.put(cup, desc.getStock());
                ((ProductDescription)productCatalog.getProducts().get(cup)).setStock((desc.getStock()-quantity));
                sale.createProductLine(desc,quantity);
                ok=true;
            }else{
                System.out.println("Error: No hi ha stock d'aquest producte");
            }
        }else{
            System.out.println("Error: El producte sel·leccionat no existeix");
        }
        return ok;
    }

    /**
     * Mètode per a realitzar el pagament d'una compra
     * @param amountTendered Indica els diners oferits pel client per a pagar la compra
     */
    public void makePayment(float amountTendered){
        sale.makePayment(amountTendered);
    }

    /**
     * Mètode que comprova si es una nova venda comprovant si sale es null o si la venda esta finalitzada
     * @return Retorna cert si la venda es nova i fals si no
     */
    public boolean isNewSale(){
        return (sale==null) || (sale.isFinished());
    }

    /**
     * Mètode que retorna el total de la venda actual
     * @return Retorna el total de la venda
     */
    public float total(){
        return sale.total();
    }

    /**
     * Metode el qual mostra el menu amb les opcions, captura la opcio per l'entrada de teclat i la retorna
     * @return Retorna un enter amb l'opció sel·leccionada
     */
    public int mostrarMenu(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        int opcio=0;
        System.out.println("####### Menu sistema TPV #######");
        System.out.println("1) Nova compra");
        System.out.println("2) Comprobar stock d'un producte");
        System.out.println("3) Llistar productes per sota del llindar definit");
        System.out.println("4) Llistar vendes del dia amb decrement d'estoc");
        System.out.println("5) Sortir");
        try{
            System.out.print("Opcio> ");
            opcio=Integer.parseInt(br.readLine());
        }catch(IOException e){
            e.printStackTrace();
        }

        return opcio;
    }

    /**
     * Mètode que realitza una nova compra, amb la possiblitat de comprar més d'un producte
     */
    public void opcioNovaCompra(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        int cup=0,quantity=0;
        float effectiveOffered=0;
        Hashtable productesComprats=new Hashtable();
        String sortir="";        
        System.out.println("Sistema de compra");
        do{
            System.out.println("Introdueix dades del producte:");
            try{
                System.out.print("ID : ");
                cup = Integer.parseInt(br.readLine());
                System.out.print("Quantitat : ");
                quantity = Integer.parseInt(br.readLine());
                if(productIntroduction(cup, quantity)){         //La introduccio del producte ha anat be
                        productesComprats.put(cup, (ProductDescription)productCatalog.getDescription(cup));
                        
                }
                System.out.print("Vols comprar un altre producte(S/N)? : ");
                sortir=br.readLine();
            }catch (Exception e){
                e.printStackTrace();
            }
        }while(!sortir.equalsIgnoreCase("n"));
        completeSale();
        System.out.println("El total de la compra es: " + total() + " €");
        generarRebut(productesComprats);
        System.out.println("Has acabat la compra, ara has de pagar, introdueix la quantitat: ");
        try{
            do{
                System.out.print("Efectiu: ");
                effectiveOffered=Float.parseFloat(br.readLine());
                makePayment(effectiveOffered);
                if(effectiveOffered<total()){
                    System.out.println("L'efectiu donat no es suficient per pagar el rebut, torna a introdur-lo");
                }else if(sale.getAmount()>0){
                    makePayment(effectiveOffered);
                    DecimalFormat df = new DecimalFormat("0.00");
                    System.out.println("Gracies per la seva compra, se li torna " + df.format((sale.getAmount())) + " € de canvi");
                }
            }while(effectiveOffered<total());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que genera un rebut de la compra actual i el mostra per pantalla
     * @param productesComprats Indica els productes que s'han comprat
     */
    public void generarRebut(Hashtable productesComprats){
        Iterator i=productesComprats.entrySet().iterator();
        System.out.println("############################");
        System.out.println("S'han comprat " + productesComprats.size() + " productes");
        System.out.println(" - - REBUT DE LA COMPRA  - - ");
        while(i.hasNext()){
            Map.Entry e=(Map.Entry)i.next();
            System.out.println("ID: " + e.getKey());
            System.out.println("PREU UNITARI: " + ((ProductDescription)e.getValue()).getPrice());
            System.out.println(" - - - - - - - - - ");
        }
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("TOTAL A PAGAR: " + df.format(total())+" €");
        System.out.println("############################");
    }

    /**
     * Mètode que mitjançant el ID del producte mostra per pantalla el seu estoc actual
     */
    public void opcioConsultarEstoc(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader (isr);
        int cup;
        ProductDescription pd=null;
        System.out.println("Introdueix cup:");
       try{
            cup=Integer.parseInt(br.readLine());
            pd=productCatalog.getDescription(cup);
            System.out.println("L'estoc del producte es: " + pd.getStock());
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Mètode que mostra per pantalla tots els productes del cataleg de productes que estan per sota del llindar definit per cada producte
     */
    public void opcioConsultarProdLlindar(){
        Enumeration e=productCatalog.getProducts().elements();
        System.out.println("Productes per sota del llindar definit: ");
        while(e.hasMoreElements()){
            ProductDescription pd=(ProductDescription)e.nextElement();
            if(!pd.checkStock()){
                System.out.println("Cup: " + pd.getCup());
                System.out.println("Description: " + pd.getDescription());
                System.out.println("Price: " + pd.getPrice());
                System.out.println("Stock: " + pd.getStock());
                System.out.println("Threshold: " + pd.getThreshold());
            }
        }
    }

    /**
     * Mètode el qual permet veure tots els productes que s'han venut en un dia
     */
    public void opcioVendesDia(){
        Iterator i=productCatalog.getProducts().entrySet().iterator();
        System.out.println("Llistat de productes que s'han venut amb el seu decrement d'estoc ("+sale.getDate().toString()+")");
        while(i.hasNext()){
            Map.Entry m=(Map.Entry)i.next();
            ProductDescription producte=(ProductDescription)m.getValue();
            if(vendesEstoc.containsKey(producte.getCup())){
                int estocVendes=(Integer)vendesEstoc.get(producte.getCup());
                int nouEstoc=estocVendes-producte.getStock();
                System.out.println("Producte amb ID: " + producte.getCup());
                System.out.println("Decrement d'estoc: " + nouEstoc);
            }
        }
    }

    /**
     * Mètode inicial el qual mostra el menu iniciant un nou cataleg de productes
     * @param args Parametres d'entrada
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcio=0;
        ProductCatalog pc=new ProductCatalog();
        TPV tpv=new TPV(pc);
        pc.readProducts();
        do{
            opcio=tpv.mostrarMenu();
            switch(opcio){
                case 1:
                    tpv.opcioNovaCompra();
                    break;
                case 2:
                    tpv.opcioConsultarEstoc();
                    break;
                case 3:
                    tpv.opcioConsultarProdLlindar();
                    break;
                case 4:
                    tpv.opcioVendesDia();
                    break;
            }
        }while(opcio!=5);
        pc.saveChanges();

    }

}


