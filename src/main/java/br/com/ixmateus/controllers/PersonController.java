package br.com.ixmateus.controllers;

import br.com.ixmateus.data.dto.PersonDTO;
import br.com.ixmateus.services.PersonServices;
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
            public List<PersonDTO> findAll() {
                return service.findAll();
            }

            // GET - Busca uma pessoa por ID
            @GetMapping("/{id}")
            public PersonDTO findById(@PathVariable Long id) {
                return service.findById(id);
            }

            // POST - Cria uma nova pessoa
           @PostMapping
            public PersonDTO create(@RequestBody PersonDTO PersonDTO){
                return service.create(PersonDTO);
           }

            // PUT - Atualiza uma pessoa existente
            @PutMapping
            public PersonDTO update(@RequestBody PersonDTO PersonDTO){
                return service.update(PersonDTO);
           }

           // DELETE - Remove uma pessoa pelo ID
           @DeleteMapping ("/{id}")
           public ResponseEntity<Void> delete(@PathVariable Long id) {
               service.delete(id);
               return ResponseEntity.noContent().build(); // HTTP 204
           }

    }