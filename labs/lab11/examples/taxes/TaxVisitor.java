public class TaxVisitor implements Visitor {

    @Override
    public double visit(Liquor liquorItem) {
        System.out.println("Liquor Item: Price with Tax");
        return liquorItem.getPrice()* 0.18 + liquorItem.getPrice();
    }

    @Override
    public double visit(Tobacco tobaccoItem) {
        System.out.println("Tobacco Item: Price with Tax");
        return tobaccoItem.getPrice()* 0.15 + tobaccoItem.getPrice();
    }   

    @Override
    public double visit(Necessity necessityItem) {
        System.out.println("Necessity Item: Price with Tax");
        return necessityItem.getPrice()* 0 + necessityItem.getPrice();

    }
    
}
