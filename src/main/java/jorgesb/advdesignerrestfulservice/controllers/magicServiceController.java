package jorgesb.advdesignerrestfulservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.magic;
import jorgesb.advdesignerrestfulservice.services.magicService;
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
@RequestMapping("/magic")
public class magicServiceController {
    
    @Autowired
    magicService service;

    @ApiOperation(value = "getAllmagics", notes = "Esta funcion nos devolvera una lista de personajes, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<magic>> getAllmagics() {
        List<magic> list = service.getAllmagics();

        return new ResponseEntity<List<magic>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getAllMagicByIdUser", notes = "Esta funcion nos devolvera la lista de personajes de un usuario por su id pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<magic>> getAllMagicByIdUser(@PathVariable("id") Long id) {
        List<magic> list = service.getAllMagicByIdUser(id);

        return new ResponseEntity<List<magic>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getMagicById", notes = "Esta funcion nos devuelve un personaje por code pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{code}")
    public ResponseEntity<magic> getMagicById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        magic entity = service.getMagicById(code);

        return new ResponseEntity<magic>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getMagicsByName", notes = "Esta funcion nos devolvera una lista de personajes buscando por el valor del nombre pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namemagic}")
    public ResponseEntity<List<magic>> getMagicsByName(@PathVariable("namemagic") String namemagic) {
        List<magic> list = service.getMagicsByName(namemagic);

        return new ResponseEntity<List<magic>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getMagicsByName", notes = "Esta funcion nos devolvera una lista de personajes buscando por el valor del nombre pasado de un usuario por su id dado. Ademas dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namemagic}/user/{id}")
    public ResponseEntity<List<magic>> getMagicsByName(@PathVariable("namemagic") String namemagic, @PathVariable("id") Long id) {
        List<magic> list = service.getMagicsByName(namemagic, id);

        return new ResponseEntity<List<magic>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "createMagic", notes = "Esta funcion creara un personaje y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<magic> createMagic(@Valid @RequestBody magic myMagic) {
        magic created = service.createMagic(myMagic);
        return new ResponseEntity<magic>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "UpdateMagic", notes = "Esta funcion actualizara un personaje y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<magic> UpdateMagic(@Valid @RequestBody magic myMagic)
            throws RecordNotFoundException {
        System.out.println(myMagic);
        magic updated = service.UpdateMagic(myMagic);
        return new ResponseEntity<magic>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "deleteMagicById", notes = "Esta funcion borrara un personaje por code pasado y dara una respuesta HTTP status")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = magic.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{code}")
    public HttpStatus deleteMagicById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        service.deleteMagicById(code);
        return HttpStatus.FORBIDDEN;
    }

}
