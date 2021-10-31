
import java.util.ArrayList;

public class Company implements Visitable {

    private ArrayList<Person> _emp = 
    new ArrayList<Person>();

    /** Construcotr for initializing the company withg some employees*/

    public Company(){
        int count = (int)(Math.random()* 100);
        for(int i = 0 ; i < count ; i++){
            _emp.add(new Person());
        }

    }

    /**@return size of company, number of employees */
    public int size(){
        return _emp.size();
    }


    /**
     *
     * @param index
     * @return an employee
     */
    public Person getEmp(int index){
        return _emp.get(index);
    }


    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
