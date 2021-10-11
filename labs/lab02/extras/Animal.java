
public class Animal{

    int _age;

    // Initializes the animal class
    public Animal(int InitialAge){
        this._age = InitialAge;
    }

    public int getAge(){
        return this._age;
    }

    public void breathe(){
        this._age++;
    }


    @Override
    public String toString(){
        return "Animal : " + this._age + " years" ;
    }
}