public class FastTrack implements FlexyIRS {


    @Override
    public double visitPerson(Person p){
        return 1;

    }

    @Override
    public double visitCompany(Company company){
        double tax = 0;

        for(int i = 0; i < company.getSize(); i++){
            tax += company.getPerson(i).accept(this);
        }

        if(company.getSize() < 4){
            tax *= 0.6;
        }
        return tax;
    }

    @Override
    public double visitRegion(Region region){

        double tax = 0;

        boolean hasDiscount = false;

        for(int i = 0; i < region.getSize(); i++){
            tax += region.getCompany(i).accept(this);
            if(region.getCompany(i).getSize() < 10){
                hasDiscount = true;
                break;
            }
        }

      
        if(hasDiscount){
            tax*= 0.80;
        }

        return tax;
    }
}
