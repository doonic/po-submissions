public class BecauseWeCare implements Visitor {

    /**
     * Low water marker for revenue.
     */
    private final int LOW = 1;

    /**
     * Low water marker for population.
     */
    private final int POP = 50;


    @Override
    public double visit(Person p) {
        return 1;
    }

    @Override
    public double visit(Company company) {
        double tax = 0;
        for (int index = 0; index < company.size(); index++)
            tax += company.getEmp(index).accept(this);
        if (company.size() < POP || tax < LOW)
            tax *= .9;
        return tax;
    }

    @Override
    public double visit(Region region) {
        double tax = 0;
        for (int index = 0; index < region.size(); index++)
            tax += region.getCompany(index).accept(this);
        if (region.size() < POP || tax < LOW)
            tax *= .9;
        return tax; 
    }
    
}
