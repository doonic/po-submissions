import java.util.ArrayList;

public class Region implements TaxPayer {

    /** Stores the companies in the region */
    private ArrayList<Company> _comp = new ArrayList<Company>();


    @Override
    public double accept(FlexyIRS irs){
        return irs.visitRegion(this);
    }


    // for testing purposes only
    public void addCompany(Company c){
        _comp.add(c);
    }
    
    public Company getCompany(int index){
        return _comp.get(index);
    }

    public int getSize(){
        return _comp.size();
    }
}
