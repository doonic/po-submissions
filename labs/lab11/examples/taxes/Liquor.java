public class Liquor implements Visitable {

    private double _price;

    public Liquor(double ItemValue){
        _price = ItemValue;
    }

    public double getPrice(){
        return _price;
    }

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }
    
}
