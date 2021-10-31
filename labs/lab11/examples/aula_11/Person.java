public class Person implements TaxPayer{

    @Override
    public double accept(FlexyIRS irs){
        return irs.visitPerson(this);

    }


    
}
