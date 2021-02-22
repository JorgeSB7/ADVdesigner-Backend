package jorgesb.advdesignerrestfulservice.controllers;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.character;
import jorgesb.advdesignerrestfulservice.services.characterService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/character")
public class characterServiceController {
    
    @Autowired
    characterService service;
    
    @GetMapping
    public ResponseEntity<List<character>> getAllcharacters() {
        List<character> list = service.getAllcharacters();

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<List<character>> getAllCharacterByIdUser(@PathVariable("id") Long id) {
        List<character> list = service.getAllCharacterByIdUser(id);

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<character> getCharacterById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        character entity = service.getCharacterById(code);

        return new ResponseEntity<character>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @GetMapping("/search/{namecharacter}")
    public ResponseEntity<List<character>> getCharactersByName(@PathVariable("namecharacter") String namecharacter) {
        List<character> list = service.getCharactersByName(namecharacter);

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{namecharacter}/user/{id}")
    public ResponseEntity<List<character>> getCharactersByName(@PathVariable("namecharacter") String namecharacter, @PathVariable("id") Long id) {
        List<character> list = service.getCharactersByName(namecharacter, id);

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<character> createCharacter(@Valid @RequestBody character myCharacter) {
        character created = service.createCharacter(myCharacter);
        return new ResponseEntity<character>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<character> UpdateCharacter(@Valid @RequestBody character myCharacter)
            throws RecordNotFoundException {
        System.out.println(myCharacter);
        character updated = service.UpdateCharacter(myCharacter);
        return new ResponseEntity<character>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public HttpStatus deleteCharacterById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        service.deleteCharacterById(code);
        return HttpStatus.FORBIDDEN;
    }

}