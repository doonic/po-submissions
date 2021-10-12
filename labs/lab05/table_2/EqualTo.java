
public class EqualTo implements SelectionPredicate{

    // the value to test against
    private int _value = 0;


    // constructor

    public EqualTo(int value){
        _value = value;
    }


    @Override
    public boolean ok(int value){
        return _value == value;
    }
}