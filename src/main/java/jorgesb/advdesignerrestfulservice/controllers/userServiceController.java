package jorgesb.advdesignerrestfulservice.controllers;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.user;
import jorgesb.advdesignerrestfulservice.services.userService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class userServiceController {
    
    @Autowired
    userService service;
    
    @GetMapping
    public ResponseEntity<List<user>> getAllusers() {
        List<user> list = service.getAllusers();

        return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<user> getUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        user entity = service.getUserById(id);

        return new ResponseEntity<user>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @GetMapping("/search/{name}")
    public ResponseEntity<List<user>> getUsersByName(@PathVariable("name") String name) {
        List<user> list = service.getUsersByName(name);

        return new ResponseEntity<List<user>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<user> createUser(@Valid @RequestBody user myUser) {
        user created = service.createUser(myUser);
        return new ResponseEntity<user>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<user> UpdateUser(@Valid @RequestBody user myUser)
            throws RecordNotFoundException {
        user updated = service.UpdateUser(myUser);
        return new ResponseEntity<user>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUserById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteUserById(id);
        return HttpStatus.ACCEPTED;
    }
    
    @GetMapping("/search/{email}/{password}")
    public ResponseEntity<user> getItemsByCount(@PathVariable("email") String email,@PathVariable("password") String password) {
        user count = service.searchCount(email, password);
 
        return new ResponseEntity<user>(count, new HttpHeaders(), HttpStatus.OK);
    }
    
     @GetMapping("/searching/{email}")
    public ResponseEntity<user> getItemsByEmail(@PathVariable("email") String email) {
        user count = service.searchEmail(email);
 
        return new ResponseEntity<user>(count, new HttpHeaders(), HttpStatus.OK);
    }

}
