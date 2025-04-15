package br.com.ixmateus.controllers;

import br.com.ixmateus.PersonServices;
import br.com.ixmateus.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping("/person")
    public class PersonController {

            @Autowired
            private PersonServices service;

            // GET - Lista todas as pessoas
            @GetMapping
            public List<Person> findAll() {
                return service.findAll();
            }

            // GET - Busca uma pessoa por ID
            @GetMapping("/{id}")
            public Person findById(@PathVariable String id) {
                return service.findById(id);
            }

            // POST - Cria uma nova pessoa
           @PostMapping
            public Person create(@RequestBody Person person){
                return service.create(person);
           }

            // PUT - Atualiza uma pessoa existente
            @PutMapping
            public Person update(@RequestBody Person person){
                return service.update(person);
           }

           // DELETE - Remove uma pessoa pelo ID
           @DeleteMapping ("/{id}")
            public void delete(@PathVariable String id){
                service.delete(id);
           }


        }