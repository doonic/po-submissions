import java.util.ArrayList;

public class Region implements Visitable{


    /** Stores company in this region  */
    private ArrayList<Company> _companies = 
    new ArrayList<Company>();

    public Region(){
        int count = (int)(Math.random()*100);

        for(int i = 0; i < count; i++)
            _companies.add(new Company());
    }

    /**
     *@return size of the region  (total number of comapanies
     located in the region)
     */
    public int size(){
        return _companies.size();
    }

    /**
     *@param index
     *return a company
     */
    public Company getCompany(int index){
        return _companies.get(index);

    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }


}
