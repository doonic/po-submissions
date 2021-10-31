public class App {
    public static void main(String[] agrs){
        TaxVisitor taxCalculate = new TaxVisitor();
        TaxVisitorHolidays taxCalculateHolidays =
        new TaxVisitorHolidays();

        Liquor beirao = new Liquor(12.99);
        Liquor licorDeMerda = new Liquor(29.99);

        Tobacco portuguesSuave = new Tobacco(45.99);
        Necessity toothBrusher = new Necessity(3.50);

        System.out.println("PRICES BEFORE HOLIDAYS");
        System.out.println(beirao.accept(taxCalculate));
        System.out.println(licorDeMerda.accept((taxCalculate)));
        System.out.println(portuguesSuave.accept(taxCalculate));
        System.out.println(toothBrusher.accept(taxCalculate));

        System.out.println("PRICES DURING HOLIDAYS");

        System.out.println(beirao.accept(taxCalculateHolidays));
        System.out.println(licorDeMerda.accept((taxCalculateHolidays)));
        System.out.println(portuguesSuave.accept(taxCalculateHolidays));
        System.out.println(toothBrusher.accept(taxCalculateHolidays));

    }
    
}
