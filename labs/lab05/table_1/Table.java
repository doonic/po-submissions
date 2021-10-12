public class Table{

    private int _table[];


    //Initialization ; Constructor

    public Table(int v){
        _table = new int[v];
    }

    /**
     * set all 
     *         values in table
     *@param value to set
     */
    public void setAll(int v){
        for(int p = 0; p < _table.lenght;p++)
            _table[p] = v;
    }


    public void print(Transform t) {
    for (int i: _table) System.out.println(t.transform(i));
  }
}