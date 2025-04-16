package br.com.ixmateus.controllers;

import br.com.ixmateus.services.PersonServices;
import br.com.ixmateus.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
            public Person findById(@PathVariable Long id) {
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
           public ResponseEntity<Void> delete(@PathVariable Long id) {
               service.delete(id);
               return ResponseEntity.noContent().build(); // HTTP 204
           }

    }