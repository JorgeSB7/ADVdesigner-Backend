package jorgesb.advdesignerrestfulservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.user;
import jorgesb.advdesignerrestfulservice.services.userService;

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
@RequestMapping("/user")
public class userServiceController {

    @Autowired
    userService service;

    @ApiOperation(value = "getAllUsers", notes = "Esta funcion nos devolvera una lista de usuarios, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<user>> getAllusers() {
        List<user> list = service.getAllusers();

        return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "getUserById", notes = "Esta funcion nos devuelve un de usuario por id pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{id}")
    public ResponseEntity<user> getUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        user entity = service.getUserById(id);

        return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "getUsersByName", notes = "Esta funcion nos devolvera una lista de usuarios buscando por el valor del nombre pasado, mas una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{name}")
    public ResponseEntity<List<user>> getUsersByName(@PathVariable("name") String name) {
        List<user> list = service.getUsersByName(name);

        return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "createUser", notes = "Esta funcion crea un usuario y devuleve una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<user> createUser(@Valid @RequestBody user myUser) {
        user created = service.createUser(myUser);
        return new ResponseEntity<user>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "UpdateUser", notes = "Esta funcion actualiza un usuario y devuleve una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<user> UpdateUser(@Valid @RequestBody user myUser)
            throws RecordNotFoundException {
        user updated = service.UpdateUser(myUser);
        return new ResponseEntity<user>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "deleteUserById", notes = "Esta funcion borra el usuario por el id pasado y devuleve una respuesta HTTP status")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteUserById(id);
        return HttpStatus.ACCEPTED;
    }
    
    @ApiOperation(value = "getItemsByCount", notes = "Esta funcion devuelve un usuario por el email y password pasado. Ademas devuleve una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{email}/{password}")
    public ResponseEntity<user> getItemsByCount(@PathVariable("email") String email, @PathVariable("password") String password) {
        user count = service.searchCount(email, password);

        return new ResponseEntity<user>(count, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "getItemsByEmail", notes = "Esta funcion devuelve un usuario por el email pasado y devuleve una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = user.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/searching/{email}")
    public ResponseEntity<user> getItemsByEmail(@PathVariable("email") String email) {
        user count = service.searchEmail(email);

        return new ResponseEntity<user>(count, new HttpHeaders(), HttpStatus.OK);
    }

}
