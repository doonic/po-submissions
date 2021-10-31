public class Tobacco implements Visitable {

    private double _price;

    public Tobacco(double tobaccoItem){
        _price = tobaccoItem;
    }

    public double getPrice(){
        return _price;
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
