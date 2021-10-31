public class App {
    public static void main(String[] args){

        FlexyIRS irss = new FastTrack();

        Region olek = new Region();

        System.out.println("FAST TRACK TAX: " + olek.accept(irss));

        Company  c   = new Company();
        Company  c2  = new Company();
        Company  c3  = new Company();
        Region   r   = new Region();
        Region   r2  = new Region();
        Region   r3  = new Region();
        FlexyIRS irs = new FastTrack();

        for (int i = 0; i < 20; i++)
            c.addPerson(new Person());


        for (int i = 0; i < 3; i++)
            c2.addPerson(new Person());


        for (int i = 0; i < 8; i++)
            c3.addPerson(new Person());

        r.addCompany(c);
        r2.addCompany(c2);
        r3.addCompany(c);
        r3.addCompany(c3);

        /** no discount whatsoever */
        System.out.printf("A região r irá pagar %f.%n", r.accept(irs));

        /** 3 employees, discount */
        System.out.printf("A região r2 irá pagar %f.%n", r2.accept(irs));

        /** discount  */
        System.out.printf("A região r3 irá pagar %f.%n", r3.accept(irs));
    


    }
}
