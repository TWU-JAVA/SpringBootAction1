package com.springboot.action.example.demo.controller;

import com.springboot.action.example.demo.bean.Animal;
import com.springboot.action.example.demo.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ApiController {
    private AnimalService animalService;

    public ApiController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @RequestMapping(value = "/getAnimal", method = RequestMethod.GET)
    public ResponseEntity<Animal> getAnimalByName(@RequestParam(value = "name") String name) {
        Animal animal = animalService.findAnimalByName(name);
        if (animal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(HttpStatus.OK).body(animal);
    }

    @RequestMapping(value = "/createAnimal", method = RequestMethod.POST)
    public String createAnimal(@RequestBody Animal animal) {
        String result = "create fail.";
        Boolean isSuccess = animalService.create(animal);
        if (isSuccess) {
            result = "create success.";
        }
        return result;
    }

    @RequestMapping(value = "/updateAnimal", method = RequestMethod.PUT)
    public String updateAnimal(@RequestBody Animal animal) {
        String result = "update fail.";
        Boolean isSuccess = animalService.update(animal);
        if (isSuccess) {
            result = "update success.";
        }
        return result;

    }

    @RequestMapping(value = "/deleteAnimal", method = RequestMethod.DELETE)
    public String deleteAnimal(@RequestParam String name) {
        String result = "delete failed.";
        Boolean isSuccess = animalService.delete(name);
        if (isSuccess) {
            result = "delete success.";
        }
        return result;
    }

}
