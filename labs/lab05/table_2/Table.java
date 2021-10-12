

public class Table{

    private int _v[];


    // constructor 

    public Table(int size){
        _v = int[size];
    }


    // setters

    public void setAll(int value){
        

        for(int i = 0; i < _v.length; i++){
            _v[i] = value;
        }
    }@

    public void setValue(int position,int value){
        if(position < _v.length){
            _v[position] = value;
        }
    }


    // getters

    /**
    *@param position 
    * get the value in a given position
    */

    public int getValue(int position){
        if(position < _v.length){
            return _v[position];
        }
    }

    public boolean contains(SelectionPredicate predicate){
        for(int i = 0; i < _v.length; i++){
            if(predicate.ok(_v[i])) return true
        }
        return false
    }
}

