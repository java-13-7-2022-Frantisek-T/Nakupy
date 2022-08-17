import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDate.now;

public class Main {

    public static final String FILENAME = "purchase.txt";

    public static void main(String[] args) {

        // Vytvoříme proměnnou pro ukládání cvičných nákupů
        // RegisterOfPurchases register = new RegisterOfPurchases();

        // Načteme obsah souboru
        RegisterOfPurchases register = null;
        try {
            register = RegisterOfPurchases.importFromFile(FILENAME);
        } catch (PurchaseException e) {
            System.err.println("Soubor " + FILENAME + "se nepodařiol správně načíst !\n" + e.getLocalizedMessage());
        }

        // Zobrazíme celkovou cenu všech nákupů
        System.out.println("Celková cena nákupů:  " + register.totalPrice());
        // Zobrazíme celkový počet nákupů
        System.out.println("Celkový počet nákupů: " + register.numberOfPurchase());
        // Vypočteme průměrnou cenu nákupu
        try {
            System.out.println("Průměrná cena nákupu: " + register.averagePrice()+" Kč.");
        }
        catch (PurchaseException e) {
            System.err.println("Nastala chyba při výpočtu průměrné ceny nákupu (dělení nulou)! " + e.getLocalizedMessage());
        };



        // Vytvoříme dva cvičné nákupy
        Purchase purchase1 = new Purchase ("Pečivo", Category.FOOD,new BigDecimal("548.10"), LocalDate.parse("2021-08-09"));
        Purchase purchase2 = new Purchase ("Benzín (28 l)", Category.CONSUMABLES, new BigDecimal("935.0"), LocalDate.parse("2021-08-08"));

        // Vyzkoušíme ošetření vyjímky při vstupu se zápornou cenou
        // purchase2.setPrice(BigDecimal.valueOf(-123456789.98));

        // Zobrazíme celkovou cenu všech nákupů
        // System.out.println("Celková cena nákupů:  " + purchases.TotalPrice());
        // Zobrazíme celkový počet nákupů
        // System.out.println("Celkový počet nákupů: " + purchases.TotalPurchase());
        // Zkusíme výpočet průměrné ceny před přidáním prvních dvou cvičných nákupů do seznamu nákupů
        // try {System.out.println("Průměrná cena nákupu: " + purchases.AveragePrice(purchases.TotalPrice(), purchases.TotalPurchase())+" Kč.");}
        // catch (PurchaseException e) {e.printStackTrace();};

        //  {throw new PurchaseException(" VÝPOČET NEPROVEDEN ");}


        // Přidáme dva cvičné, již dříve vytvořené, nákupy do seznamu nákupů
        register.addPurchase(purchase1);
        register.addPurchase(purchase2);


        // Nastavíme datum od kterého se mají vypisovat nákupy na konzoli tak, že jeden nákup nebude vypisován
        register.showPurchaseFromDate(LocalDate.parse("2021-01-01"));

        // Zobrazíme celkovou cenu všech nákupů
        System.out.println("Celková cena nákupů:  " + register.totalPrice());
        // Zobrazíme celkový počet nákupů
        System.out.println("Celkový počet nákupů: " + register.numberOfPurchase());
        // Vypočteme průměrnou cenu nákupu
        try {
            System.out.println("Průměrná cena nákupu: " + register.averagePrice()+" Kč.");
        }
        catch (PurchaseException e) {
            System.err.println("Nastala chyba při výpočtu průměrné ceny nákupu (dělení nulou)! " + e.getLocalizedMessage());
            //throw new RuntimeException(e);
            //e.printStackTrace();
        };

//        System.out.println("Hello world!\n"+now().getDayOfMonth()+" - "+((now().getDayOfYear())-31-28-31-30-31-30-31-0));

    }
}