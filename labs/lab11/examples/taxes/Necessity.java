public class Necessity implements Visitable {

    private double _price;

    public Necessity(double necessityItem){
        _price = necessityItem;
    }

    public double getPrice(){
        return _price;
    }
    
    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
