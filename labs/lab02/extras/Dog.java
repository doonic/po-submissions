public class Dog{


    /*We define the base energy as a constant,but it does not have to be this 
    way. It could be defined differently for each dog(but this requirement does not 
    exist in this case)*/ 
    

    /* the dog's base energy level */
    private static final int BASE_ENERGY = 1000;
    /* the dog's name */
    private String _name;

    /* the dog's current energy value */
    private int _energy = BASE_ENERGY;

    /* Initialize a dog with a name. Default energy levels are used 
    *
    *@param name
    */
    public Dog(String Name){
        _name = name;
    }


    /*return dog's current energy level*/
    public int getEnergy(){

        return _energy;
    }

    /*return dog's name*/
    public String getName(){
        return _name;
    }


    /* Set the dog's name
    *
    *@param name, the dog's name
    */

    public void setName(String name){
        _name = name;
    }


    /*When a dog runs, the energy decreases by 50 units. This value could be 
    defined as an attrivute or as a constant.
    *
    *@return whether the dog was able to run or not
    */

    public boolean run(){
        if(_energy < 50){
            return false;
        
        }
        _energy -= 0;
        return true;
    }

    /* Call "run" to account for spent energy
    *
    *@param mouse 
    *           the mouse to be chased
    *
    *@return whether or not the dog was able to catch the mouse. 
    *if the mouse escaped, its energy increases
    */

    public boolean caughtMouse(Mouse mouse){
        run();
        mouse.run();// accounts for the spent energy, when the mouse is being followed, it runs.

        if( 0 == (int)(25*Math.random())){
            return true;
        }
        mouse.escaped();
        return false;
    }

    /*
     *@param mouse 
     *             the mouse to eat
     *
     */
     public void eatMouse
    
}