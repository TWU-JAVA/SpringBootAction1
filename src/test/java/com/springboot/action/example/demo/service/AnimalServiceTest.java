package com.springboot.action.example.demo.service;

import com.springboot.action.example.demo.bean.Animal;
import com.springboot.action.example.demo.dao.AnimalDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class AnimalServiceTest {

    private AnimalService animalService = new AnimalService();
    @Mock
    private AnimalDao animalDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Field animalDaoField = ReflectionUtils.findField(AnimalService.class, "animalDao");
        ReflectionUtils.makeAccessible(animalDaoField);
        ReflectionUtils.setField(animalDaoField, animalService, animalDao);
    }

    @Test
    public void shouldFindOneAnimalWhenParamAnimalNameIsAvailable(){
        String name = "dog";
        given(animalDao.findObjectByName(name)).willReturn(new Animal("dog",2,4));

        Animal animal = animalService.findAnimalByName(name);

        assertEquals(name,animal.getName());
    }

    @Test
    public void shouldReturnNullWhenFindAnimalAndParamAnimalNameIsNotExistInDB(){
        String name = "pig";
        given(animalDao.findObjectByName(name)).willReturn(new Animal(null,0,0));

        Animal animal = animalService.findAnimalByName(name);

        assertEquals(null,animal);
    }

    @Test
    public void shouldReturnNullWhenFindAnimalAndParamAnimalNameIsEmptyString(){
        String name = "";

        Animal animal = animalService.findAnimalByName(name);

        assertEquals(null,animal);
    }

    @Test
    public void shouldReturnNullWhenFindAnimalAndParamAnimalNameIsNull(){
        String name = null;

        Animal animal = animalService.findAnimalByName(name);

        assertEquals(null,animal);
    }

    @Test
    public void shouldCreateOneAnimalWhenParamAnimalIsANewAnimal(){
        String name = "pig";
        Animal animal = new Animal(name,2,4);
        given(animalDao.findObjectByName(name)).willReturn(new Animal(null,0,0));
        given(animalDao.create(animal)).willReturn(true);

        Boolean result = animalService.create(animal);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenCreateAndParamAnimalIsNull(){
        Animal animal = null;

        Boolean result = animalService.create(animal);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenCreateAndParamAnimalIsExistInDB(){
        String name = "dog";
        Animal animal = new Animal(name,2,4);
        given(animalDao.findObjectByName(name)).willReturn(new Animal("dog",2,4));

        Boolean result = animalService.create(animal);

        assertFalse(result);
    }

    @Test
    public void shouldUpdateAnimalWhenParamAnimalIsExistInDB(){
        String name = "dog";
        Animal animal = new Animal(name,2,4);
        given(animalDao.findObjectByName(name)).willReturn(new Animal(name,1,4));
        given(animalDao.update(animal)).willReturn(true);

        Boolean result = animalService.update(animal);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenUpdateAndParamAnimalIsNull(){
        Animal animal = null;

        Boolean result = animalService.update(animal);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenUpdateAndParamAnimalIsNotExistInDB(){
        String name = "dog";
        Animal animal = new Animal(name,2,4);
        given(animalDao.findObjectByName(name)).willReturn(new Animal(null,0,0));

        Boolean result = animalService.update(animal);

        assertFalse(result);
    }

    @Test
    public void shouldDeleteAnimalWhenParamAnimalIsExistInDB(){
        String name = "dog";
        given(animalDao.findObjectByName(name)).willReturn(new Animal(name,1,4));
        given(animalDao.delete(name)).willReturn(true);

        Boolean result = animalService.delete(name);

        assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenDeleteAnimalAndParamNameIsNotNullAndExistInDB(){
        String name = "dog";
        given(animalDao.findObjectByName(name)).willReturn(new Animal(null,0,0));
        given(animalDao.delete(name)).willReturn(false);

        Boolean result = animalService.delete(name);

        assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenDeleteAnimalAndParamNameIsNull(){
        String name = null;
        given(animalDao.findObjectByName(name)).willReturn(null);
        given(animalDao.delete(name)).willReturn(false);

        Boolean result = animalService.delete(name);

        assertFalse(result);
    }


}