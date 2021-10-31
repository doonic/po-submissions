public class Person implements Visitable{

    @Override
    public double accept(Visitor visitor) {
        return visitor.visit(this);
    }

    
}
