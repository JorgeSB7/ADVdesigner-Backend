package jorgesb.advdesignerrestfulservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.character;
import jorgesb.advdesignerrestfulservice.services.characterService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/character")
public class characterServiceController {

    @Autowired
    characterService service;

    @ApiOperation(value = "getAllcharacters", notes = "Esta funcion nos devolvera una lista de personajes, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<character>> getAllcharacters() {
        List<character> list = service.getAllcharacters();

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getAllCharacterByIdUser", notes = "Esta funcion nos devolvera la lista de personajes de un usuario por su id pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<character>> getAllCharacterByIdUser(@PathVariable("id") Long id) {
        List<character> list = service.getAllCharacterByIdUser(id);

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getCharacterById", notes = "Esta funcion nos devuelve un personaje por code pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{code}")
    public ResponseEntity<character> getCharacterById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        character entity = service.getCharacterById(code);

        return new ResponseEntity<character>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getCharactersByName", notes = "Esta funcion nos devolvera una lista de personajes buscando por el valor del nombre pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namecharacter}")
    public ResponseEntity<List<character>> getCharactersByName(@PathVariable("namecharacter") String namecharacter) {
        List<character> list = service.getCharactersByName(namecharacter);

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getCharactersByName", notes = "Esta funcion nos devolvera una lista de personajes buscando por el valor del nombre pasado de un usuario por su id dado. Ademas dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namecharacter}/user/{id}")
    public ResponseEntity<List<character>> getCharactersByName(@PathVariable("namecharacter") String namecharacter, @PathVariable("id") Long id) {
        List<character> list = service.getCharactersByName(namecharacter, id);

        return new ResponseEntity<List<character>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "createCharacter", notes = "Esta funcion creara un personaje y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<character> createCharacter(@Valid @RequestBody character myCharacter) {
        character created = service.createCharacter(myCharacter);
        return new ResponseEntity<character>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "UpdateCharacter", notes = "Esta funcion actualizara un personaje y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<character> UpdateCharacter(@Valid @RequestBody character myCharacter)
            throws RecordNotFoundException {
        System.out.println(myCharacter);
        character updated = service.UpdateCharacter(myCharacter);
        return new ResponseEntity<character>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "deleteCharacterById", notes = "Esta funcion borrara un personaje por code pasado y dara una respuesta HTTP status")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = character.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{code}")
    public HttpStatus deleteCharacterById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        service.deleteCharacterById(code);
        return HttpStatus.FORBIDDEN;
    }

}
