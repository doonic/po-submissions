

public class Application{
    public static void main(String[] args){
        Cat cat = new Cat("Tareco",12,4.25);
        System.out.println(cat);
        System.out.println(cat.equals(new Cat("Tareco",12,4.25)));
        System.out.println(cat.equals(new Cat("Pieter",4,1.23)));
    }
}