package jorgesb.advdesignerrestfulservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import javax.validation.Valid;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.beast;
import jorgesb.advdesignerrestfulservice.services.beastService;
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
@RequestMapping("/beast")
public class beastServiceController {

    @Autowired
    beastService service;

    @ApiOperation(value = "getAllbeats", notes = "Esta funcion nos devolvera una lista de bestias, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<beast>> getAllbeasts() {
        List<beast> list = service.getAllbeasts();

        return new ResponseEntity<List<beast>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getAllBeastByIdUser", notes = "Esta funcion nos devolvera la lista de bestias de un usuario por su id pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<beast>> getAllBeastByIdUser(@PathVariable("id") Long id) {
        List<beast> list = service.getAllBeastByIdUser(id);

        return new ResponseEntity<List<beast>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getBeastById", notes = "Esta funcion nos devuelve una bestia por code pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{code}")
    public ResponseEntity<beast> getBeastById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        beast entity = service.getBeastById(code);

        return new ResponseEntity<beast>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getBeastsByName", notes = "Esta funcion nos devolvera una lista de bestias buscando por el valor del nombre pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namebeast}")
    public ResponseEntity<List<beast>> getBeastsByName(@PathVariable("namebeast") String namebeast) {
        List<beast> list = service.getBeastsByName(namebeast);

        return new ResponseEntity<List<beast>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getBeastsByName", notes = "Esta funcion nos devolvera una lista de bestias buscando por el valor del nombre pasado de un usuario por su id dado. Ademas dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namebeast}/user/{id}")
    public ResponseEntity<List<beast>> getBeastsByName(@PathVariable("namebeast") String namebeast, @PathVariable("id") Long id) {
        List<beast> list = service.getBeastsByName(namebeast, id);

        return new ResponseEntity<List<beast>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "createBeast", notes = "Esta funcion creara una bestia y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<beast> createBeast(@Valid @RequestBody beast myBeast) {
        beast created = service.createBeast(myBeast);
        return new ResponseEntity<beast>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "UpdateBeast", notes = "Esta funcion actualizara una bestia y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<beast> UpdateBeast(@Valid @RequestBody beast myBeast)
            throws RecordNotFoundException {
        System.out.println(myBeast);
        beast updated = service.UpdateBeast(myBeast);
        return new ResponseEntity<beast>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "deleteBeastById", notes = "Esta funcion borrara una bestia por code pasado y dara una respuesta HTTP status")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = beast.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{code}")
    public HttpStatus deleteBeastById(@PathVariable("code") Long code)
            throws RecordNotFoundException {
        service.deleteBeastById(code);
        return HttpStatus.FORBIDDEN;
    }

}
