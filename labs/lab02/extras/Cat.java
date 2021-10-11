// a cat is animal 
public class Cat extends Animal{
    private String _name  = "";

    public Cat(String name,int age){
        super(age);
        _name = name;
        
    }

    @Override
    public String toString(){
        return "Cat's name : " + this._name + " age :" + this._age;
    }
}