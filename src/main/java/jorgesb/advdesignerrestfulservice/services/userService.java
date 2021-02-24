package jorgesb.advdesignerrestfulservice.services;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.user;
import jorgesb.advdesignerrestfulservice.repositories.userRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    
    @Autowired
    userRepository repository;
    
    // Devuelve todos los usuarios que haya en la base de datos
    public List<user> getAllusers() {
        List<user> userList = repository.findAll();

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<user>();
        }
    }

    // Devuelve el usuario por el id pasado
    public user getUserById(Long id) throws RecordNotFoundException {
        Optional<user> user = repository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            // Si no lo encuentra lanza la excepcion
            throw new RecordNotFoundException("No user record exist for given id", id);
        }
    }

    // Crea el usuario que le hemos pasado
    public user createUser(user entity) {
        entity = repository.save(entity);
        return entity;
    }

    // Recibe un usuario y lo actualizamos
    public user UpdateUser(user entity) throws RecordNotFoundException {

        if (entity.getId() != null) {
            Optional<user> user = repository.findById(entity.getId());

            if (user.isPresent()) {
                user newEntity = user.get();
                //newEntity.setId(entity.getId());
                newEntity.setName(entity.getName());
                newEntity.setEmail(entity.getEmail());
                newEntity.setPassword(entity.getPassword());
                newEntity.setAvatar(entity.getAvatar());
                newEntity.setCha(entity.getCha());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("User not found", entity.getId());
            }
        } else {
            throw new RecordNotFoundException("No id of user given", 0l);
        }
    }
    
    // Borramos el usuario por id pasado
    public void deleteUserById(Long id) throws RecordNotFoundException {
        Optional<user> user = repository.findById(id);

        if (user.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user record exist for given id", id);
        }
    }
    
    // Buscar por nombre de usuario
    public List<user> getUsersByName(String name) {
        List<user> userList = repository.getByName(name);

        if (userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<user>();
        }
    }
    
    public user searchCount(String email, String password){
        user p= repository.searchCount(email,password);
        if(p!=null){
            return p;
        }else{
            return new user();
        }
    }
    
    public user searchEmail(String email){
        user p= repository.searchEmail(email);
        if(p!=null){
            return p;
        }else{
            return new user();
        }
    }
}
