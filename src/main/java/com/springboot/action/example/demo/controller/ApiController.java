package com.springboot.action.example.demo.controller;

import com.springboot.action.example.demo.bean.Animal;
import com.springboot.action.example.demo.dao.AnimalDao;
import com.springboot.action.example.demo.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private AnimalService animalService;

    public ApiController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(value = "/getAnimal", method = RequestMethod.GET)
    public Animal getAnimalByName(@RequestParam(value = "name") String name) {

//        return (Animal)jdbcTemplate.queryForObject("select * from animal where name =?",
//                new Object[]{name},
//                Animal.class);
        Animal animal = animalService.findAnimalByName(name);
        if(animal!=null)
        {
         return animal;
        }
        return null;
    }

    @RequestMapping(value = "/createAnimal", method = RequestMethod.POST)
    public String createAnimal(@RequestBody Animal animal) {
        String result = "create fail.";
        Boolean isSucess = animalService.create(animal);
        if(isSucess){
            result = "create sucesses.";
        }
        return result;
    }

    @RequestMapping(value = "/updateAnimal", method = RequestMethod.PUT)
    public String updateAnimal(@RequestBody Animal animal) {
        String result = "update fail.";
        Boolean isSucess = animalService.update(animal);
        if(isSucess){
            result = "update sucesses.";
        }
        return result;

    }

    @RequestMapping(value = "/deleteAnimal", method = RequestMethod.DELETE)
    public String deleteAnimal(@RequestParam String name) {

        String result = "delete failed.";
        Boolean isSucess = animalService.delete(name);
        if(isSucess){
            result = "delete sucesses.";
        }
        return result;
    }

}
