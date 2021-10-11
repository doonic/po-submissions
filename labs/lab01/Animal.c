#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Animal.h"


#define MAXANIMALNAME 16

struct animal{

    char _name[MAXANIMALNAME];
    int _age;
    double _weight;
};


void destroyAnimal(Animal animal){
    if(animal){
        free(animal);
    }
}


Animal newAnimal(const char *name, int age, double weight){

    Animal animal = (Animal)malloc(sizeof(struct animal));

    if(animal != NULL){
        strcpy(animal->_name,name);
        animal->_age = age;
        animal->_weight = weight;
    }
    return animal;
}


const char *getAnimalName(Animal animal){
    return animal->_name;
}   


int getAnimalAge(Animal animal){
    return animal->_age;
}


double getAnimalWeight(Animal animal){
    return animal->_weight;
}


int equalsAnimal(Animal animal_1, Animal animal_2){
    if(animal_1 == NULL || animal_2 == NULL){
        return 0;
    }
    else{
        return !strcmp(getAnimalName(animal_1),getAnimalName(animal_2)) 
        && getAnimalAge(animal_1) == getAnimalAge(animal_2) 
        && getAnimalWeight(animal_1) == getAnimalWeight(animal_2);
    }
}
