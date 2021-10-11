

public class Cat{
    // the cat's attributes
    private String name;
    private int age;
    private double weight;


    // specify the cat's initial state

    /*
    *@param name
    *@param age
    *@param weight
    */
    public Cat(String name, int age, double weight){
        this.name = name;
        this.age = age; 
        this.weight = weight;

    }


    // getters

    public String getCatName(){
        return this.name;
    }

    public int getCatAge(){
        return this.age;
    }

    public double getCatWeight(){
        return this.weight;
    }

    //setters

    public void setCatName(String name){
        this.name = name;
    }

    public void setCatAge(int age){
        this.age = age;
    }

    public void setCatWeight(double weight){
        this.weight = weight;
    }


    @Override
    public boolean equals(Object obj){
        if(obj instanceof Cat){
            Cat cat = (Cat) obj;
            return this.name.equals(cat.name) && this.age == cat.age &&
            this.weight == cat.weight;
        }
        else{
            return false;
        }

    }


    @Override
	public String toString() {
		return this.name + " (cat) (" + this.age + ":" + this.weight + ")";
	}
}