import java.math.BigDecimal;
import java.time.LocalDate;



/*
    Popis nákupu —
        co se nakupovalo (description)
        Kategorii zboží (jídlo, domácnost, jiné)(category: food, consumables, others)
        Výchozí hodnota bude „others“.
        Částku za nákup (price)
        Datum, kdy byl nákup proveden (purchase date)
*/

public class Purchase {

    // Atributy
    private String      description;
    private Category    category;
    private BigDecimal  price;
    private LocalDate   purchaseDate;

    // Konstruktor
    public Purchase(String description, Category category, BigDecimal price, LocalDate purchaseDate) {

        this.description    = "";
        this.category       = Category.OTHERS;
        this.price          = BigDecimal.ZERO;
        this.purchaseDate   = LocalDate.now();

        if (price.compareTo(BigDecimal.ZERO)<0) {
            try {
                throw new PurchaseException("Záporná cena nákupu není správná!");
            }
            catch (PurchaseException e) {
                System.err.println(" Nákup se zápornou cenou akceptován ");
            }
        }

        this.description    = description;
        this.category       = category;
        this.price          = price;
        this.purchaseDate   = purchaseDate;


    }

    // Get a Set metody pro jednotlivé atributy
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}

    public BigDecimal getPrice() {return price;}

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO)<0) {
            try {
                throw new PurchaseException("Záporná cena nákupu není správná!");
            }
            catch (PurchaseException e) {
                System.err.println(" Nákup se zápornou cenou akceptován ");
            }
        }
        this.price = price;
    }

    public LocalDate getPurchaseDate() {return purchaseDate;}
    public void setPurchaseDate(LocalDate purchaseDate) {this.purchaseDate = purchaseDate;}

    // Zobrazení nákupu na obrazovce
    public void showPurchase (Purchase purchase){
        /*
        System.out.println( "Popis: " + purchase.getDescription() + "\n" +
                            "druh : " +purchase.getCategory() + "\n" +
                            "cena : " +purchase.getPrice() + "\n" +
                            "datum: " +purchase.getPurchaseDate());
         */
        System.out.println( purchase.getPurchaseDate() + " " + purchase.getCategory().name()+ " " + purchase.getDescription() + " (" +purchase.getPrice() + ")");
//        System.out.println( purchase.getPurchaseDate() + " " + purchase.getDescription() + " (" +purchase.getPrice() + ")");
    }

}



///*
//    BigDecimal a = new BigDecimal(-100);
//    BigDecimal b = BigDecimal.ZERO;
//    System.out.println(a.toString() + " " + b.toString() + " result ==> " + a.compareTo(b));          // -1
//
//    BigDecimal aa = new BigDecimal(0);
//    BigDecimal bb = BigDecimal.ZERO;
//    System.out.println(aa.toString() + " " + bb.toString() + " result ==> " + aa.compareTo(bb));      //  0
//
//    BigDecimal aaa = new BigDecimal(100);
//    BigDecimal bbb = BigDecimal.ZERO;
//    System.out.println(aaa.toString() + " " + bbb.toString() + " result ==> " + aaa.compareTo(bbb));   //  1
//
//        console print
//
//        result ==> 1
//
//        compareTo() returns
//
//        1 if a is greater than b
//                -1 if a is less than b
//        0 if a is equal to b
//
//        now for your problem you can use
//
//        if (value.compareTo(BigDecimal.ZERO) > 0)
//*/
