public class App {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Company c1 = new Company();
        Region r1 = new Region();
        Person p1 = new Person();
        
        VanillaTaxes vt = new VanillaTaxes();
        BecauseWeCare ct = new BecauseWeCare();
        
        System.out.println("Company taxes (vanilla): " + c1.accept(vt));
        System.out.println("Region taxes (vanilla): " + r1.accept(vt));
        System.out.println("Person taxes (vanilla): " + p1.accept(vt));

        System.out.println("Company taxes (care): " + c1.accept(ct));
        System.out.println("Region taxes (care): " + r1.accept(ct));
        System.out.println("Person taxes (care): " + p1.accept(ct));
    }

}