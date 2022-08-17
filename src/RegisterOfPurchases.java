/*
Pro evidenci si připravíme třídu, která bude zahrnovat:
        Všechny dosud provedené nákupy
        Možnost přidání nového nákupu
        Možnost odstranění zadaného nákupu
        Možnost získat seznam nákupů, které byly provedeny po zadaném datu.

        Statistické metody:
        celkovou cenu všech nákupů
        počet všech nákupů
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfPurchases {

    public static RegisterOfPurchases importFromFile (String filename) throws PurchaseException {
        // 1. přečíst řádky ze souboru
        // 2. Rozdělit řádky na jednotlivé položky a převést je na odpovídající datový typ
        // 3. Sestavit z jednotlivých proměnných objekty

        RegisterOfPurchases result = new RegisterOfPurchases();

        String line = "";

        // 1. Čtení ze souboru: Scanner - pomocí try-with-resources
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();                // přečte řádek
                System.out.println(line);                 // vypíše ho na obrazovku
                String[] items = line.split(";");   // vrací pole (array)
                if (items.length !=4) {
                    throw new PurchaseException("Na řádku: "+ line +" je špatný počet položek: " + items.length);
                }
                // Uložíme položky do proměnných:
                String dataAsText       = items[0]; // První položka: datum
                String priceAsText      = items[1]; // Druhá položka: cena
                String categoryAsText   = items[2]; // Třetí položka: kategorie
                String description      = items[3]; // Čtvrta položka: popis nákupu
                // Převedeme položky na správný datový typ:
                LocalDate   date        = LocalDate.parse(dataAsText);      // Převede text na datum
                BigDecimal  price       = new BigDecimal(priceAsText);      // Převede text na číslo bez ztráty přesnosti čísla
                Category    category    = Category.valueOf(categoryAsText); // Převede text na kategorii
                // Vytvoříme objekt třídy Purchase
                Purchase purchase = new Purchase (description, category, price, date);
                // Přidáme nově vytvořený objekt do kolekce
                result.addPurchase(purchase);
            }
        } catch (FileNotFoundException e) {
            throw new PurchaseException ("Soubor " + filename + " nebyl nalezen: " + e.getLocalizedMessage());
        } catch (DateTimeParseException e) {
            throw new PurchaseException ("Špatný formát data na řádku: " + line + " " + e.getLocalizedMessage());
        }

        return result;
    }

    // Vytvoření pole pro ukládání jednotlivývh nákupů
    List<Purchase> purchaseList = new ArrayList<>();

    // Přidání prvku-nákupu do seznamu
    public void addPurchase(Purchase newPurchase) {purchaseList.add(newPurchase);}

    // Odebrání prvku-nákupu do seznamu
    public void removePurchase(Purchase purchase) {purchaseList.remove(purchase);}

    // předávám KOPII atributu !!!!!
    public List<Purchase> getList (){
        return new ArrayList<>(purchaseList);
    }

    // Celková cena všech nákupů
    public BigDecimal totalPrice() {

        // Pomocná proměnná pro nasčítání cen všech nákupů
        BigDecimal result = BigDecimal.ZERO;

        for (Purchase purchase: purchaseList) {
            result = result.add(purchase.getPrice());
        }
        return result;
    }

    // Celkový počet všech nákupů
    public long numberOfPurchase() {return purchaseList.size();}

    // Průměrná cena nákupu
    public BigDecimal averagePrice() throws PurchaseException {
        long number = numberOfPurchase();
        if (number <= 0) {
            throw new PurchaseException("Průměrnou cenu nelze počítat pokud nejsou zadány žádné nákupy");
        }

        return totalPrice().divide(BigDecimal.valueOf(numberOfPurchase()), RoundingMode.HALF_UP);

    }
/*
    public BigDecimal averagePrice(BigDecimal totalPrice, Long totalCount ) throws PurchaseException {

        if (totalCount==0) {
            throw new PurchaseException("Nulový počet nákupů. Výpočet nelze provést (nulou nelze dělit)");
        }
        return totalPrice.divide(BigDecimal.valueOf(totalCount));
    }
*/

    // Seznam nákupů, které byly provedeny po zadaném datu.
    public void showPurchaseFromDate(LocalDate dateFrom) {
        for (Purchase purchase: purchaseList) {
            //  purchase.listIterator();
            int r1;
            r1 = purchase.getPurchaseDate().getYear();
            int r0;
            r0 = dateFrom.getYear();

            int m1;
            m1 = purchase.getPurchaseDate().getMonthValue();
            int m0;
            m0 = dateFrom.getMonthValue();

            int d1;
            d1 = purchase.getPurchaseDate().getDayOfYear();
            int d0;
            d0 = dateFrom.getDayOfYear();

            if ((r1>r0) | ((r1<=r0) && (m1>m0))| ((r1<=r0) && (m1<=m0) && (d1>d0)))   {
                purchase.showPurchase(purchase);
            }
        }
    }
}

/*


    // Konstruktor
    // public RegisterOfPurchases(ArrayList<Purchase> purchases) {this.purchaseList = purchases;}

    // Vrácení seznamu nákupů
    // public ArrayList<Purchase> getPurchases() {return purchaseList;}

    // Nastavení seznamu nákupů
    // public void setPurchases(ArrayList<Purchase> purchases) {this.purchaseList = purchases;}


 */