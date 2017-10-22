package com.springboot.action.example.demo.service;

import com.springboot.action.example.demo.bean.Animal;
import com.springboot.action.example.demo.dao.AnimalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    @Autowired
    private AnimalDao animalDao;

    public Animal findAnimalByName(String name) {
        Animal result = animalDao.findObjectByName(name);
        if (name == null || name == "" || result.getName() == null) {
            result = null;
        }
        return result;
    }

    public Boolean create(Animal animal) {
        Boolean result = false;
        if (animal != null && animalDao.findObjectByName(animal.getName()).getName() == null) {
            result = animalDao.create(animal);
        }
        return result;
    }

    public Boolean delete(String name) {
        Boolean result = false;
        if (name != null && animalDao.findObjectByName(name).getName() != null) {
            result = animalDao.delete(name);
        }
        return result;
    }

    public Boolean update(Animal animal) {
        Boolean result = false;
        if (animal != null && animalDao.findObjectByName(animal.getName()).getName() != null) {
            result = animalDao.update(animal);
        }
        return result;
    }

}
