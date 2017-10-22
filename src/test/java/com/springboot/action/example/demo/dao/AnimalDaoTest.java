package com.springboot.action.example.demo.dao;

import com.springboot.action.example.demo.bean.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimalDaoTest {

    @Autowired
    private AnimalDao animalDao;

    @Test
    public void sholdFindOneAnimalWhenGivenAnimalName(){
        Animal animal = animalDao.findObjectByName("dog");
        assertNotNull(animal);
    }

    @Test
    public void shouldCreateAnimalWhenGivenOneAnimal() {
        animalDao.delete("wolf");
        Animal newAnimal = new Animal("wolf",1,4);
        assertEquals(true,animalDao.create(newAnimal));
        assertNotNull(animalDao.findObjectByName("wolf"));

    }

    @Test
    public void shouldUpdateAnimalWhenGivenOneAnimal() {
        animalDao.delete("wolf");
        Animal newAnimal = new Animal("wolf",1,4);
        animalDao.create(newAnimal);
        animalDao.update(new Animal("wolf",2,4));
        assertEquals(2,animalDao.findObjectByName("wolf").getAge());
    }

    @Test
    public void shouldDeleteAnimalWhenGivenAnimalName(){
        Animal newAnimal = new Animal("dolphin",1,0);
        animalDao.create(newAnimal);
        assertEquals(true,animalDao.delete("dolphin"));
        assertEquals(null,animalDao.findObjectByName("dolphin"));
    }



}
