package br.com.ixmateus.controllers;

import br.com.ixmateus.data.dto.v1.PersonDTO;
import br.com.ixmateus.data.dto.v2.PersonDTOV2;
import br.com.ixmateus.services.PersonServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

    @RestController
    @RequestMapping("/api/person/v1")
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
                var person = service.findById(id);
                person.setBirthDay(new Date());
                // person.setPhoneNumber("+55 (34) 98765-4321");
                person.setPhoneNumber("");
                person.setLastName(null);
                person.setSensitiveData("Foo Bar");
                return person;
            }

            // POST - Cria uma nova pessoa
           @PostMapping
            public PersonDTO create(@RequestBody PersonDTO PersonDTO){
                return service.create(PersonDTO);
            }

            // Atualização para V2!
           @PostMapping ("/v2")
            public PersonDTOV2 createV2(@RequestBody @Valid PersonDTOV2 personDTO){
            return service.createV2(personDTO);
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