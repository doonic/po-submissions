
import java.util.ArrayList;

public class Company implements TaxPayer {


    /** Stores employees */
    private ArrayList<Person> _emp = new ArrayList<Person>();



    @Override
    public double accept(FlexyIRS irs){
        return irs.visitCompany(this);
    }
    
    // only for testing purposes

    public void addPerson(Person p){
        _emp.add(p);
    }

    public Person getPerson(int index){
        return _emp.get(index);
    }

    public int getSize(){
        return _emp.size();
    }
}
