public class Apptest{
    public static void main(String[] args){
        Animal a = new Animal(90);
        Cat b = new Cat("Ritchie",90);

        a.breathe();
        b.breathe();

        System.out.println(a);
        System.out.println(b);
        

    }
}