public class VanillaTaxes implements Visitor{
    
    
    @Override
    public double visit(Person p) {
        return 1;
        
    }

    @Override
    public double visit(Company c) {
        double tax = 0;
        for(int index = 0; index < c.size(); index++){
            tax += c.getEmp(index).accept(this);
        }
        return tax;
     
    }

    @Override
    public double visit(Region r) {
        double tax = 0;
        for(int index = 0; index < r.size();index++){
            tax += r.getCompany(index).accept(this);
    
        }
        return tax ;
    
    }
}
