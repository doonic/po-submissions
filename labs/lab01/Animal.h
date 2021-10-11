#ifndef _ANIMAL_H_
#define _ANIMAL_H_

typedef struct animal *Animal;

/* function prototypes*/

Animal newAnimal(const char *name, int age,double weight);
void destroyAnimal(Animal animal); 



int getAnimalAge(Animal animal);
double getAnimalWeight(Animal animal);
const char *getAnimalName(Animal animal);

void printAnimal(Animal animal);

int equalsAnimal(Animal animal_1, Animal animal_2);



#endif