public class AndGate2{
    private boolean a = false;
    private boolean b = false;


    // default constructor

    public AndGate2(){
        // does nothing
    }

    // first entry value specified
    // both entries now have the same value
    public AndGate2(boolean value){
        a = b = value;
    }

    public AndGate2(boolean value_a, boolean value_b){
        a = value_a;
        b = value_b;
    }

    // set the logical values of entries
    // setters

    public void setEntryA(boolean a){
        this.a = a;
    }

    public void setEntries(boolean a,boolean b){
        this.a = a;
        this.b = b;
    }

    // get the logical values of entries
    // getters

    public boolean getEntryA(){
        return a;
    }

    public boolean getEntryB(){
        return b;
    }

    public boolean getOutput(){
        return a && b;
    }


    @Override
    public boolean equals(Object o){
        if(o instanceof AndGate2){
            AndGate2 andgate = (AndGate2) (o);
            return a == andgate.getEntryA() && b == andgate.getEntryB();
        }
        return false;
    }

    @Override
    public String toString(){
        return "A:" + a + " b:" + b;
    }
}