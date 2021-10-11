#include <stdio.h>
#include "Animal.h"


int main(){

    Animal animal_1 = newAnimal("Tareco",12,3.4);
    Animal animal_2 = newAnimal("Piloto",1,12.3);

    printAnimal(animal_1);
    printAnimal(animal_2);

    printf("animal_1 == animal_2  %s",equalsAnimal(animal_1,animal_2) ? "yes": "no");
    printf("animal_1 == animal_1 %s",equalsAnimal(animal_1,animal_1) ? "yes": "no");

    destroyAnimal(animal_1);
    destroyAnimal(animal_2);

    return 0;
}