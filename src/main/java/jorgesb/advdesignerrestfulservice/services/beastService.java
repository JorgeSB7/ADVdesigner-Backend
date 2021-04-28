package jorgesb.advdesignerrestfulservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.beast;
import jorgesb.advdesignerrestfulservice.repositories.beastRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class beastService {
    
    @Autowired
    beastRepository repository;
    
    // Devuelve todos las bestias que haya en la base de datos
    public List<beast> getAllbeasts() {
        List<beast> beastList = repository.findAll();

        if (beastList.size() > 0) {
            return beastList;
        } else {
            return new ArrayList<beast>();
        }
    }
    
    // Devuelve todos las bestias de un usuario
    public List<beast> getAllBeastByIdUser(Long id) {
        List<beast> beastList = repository.getAllBeastByIdUser(id);

        if (beastList.size() > 0) {
            return beastList;
        } else {
            return new ArrayList<beast>();
        }
    }

    // Devuelve la bestia por el id pasado
    public beast getBeastById(Long codeb) throws RecordNotFoundException {
        Optional<beast> beast = repository.findById(codeb);

        if (beast.isPresent()) {
            return beast.get();
        } else {
            // Si no lo encuentra lanza la excepcion
            throw new RecordNotFoundException("No beast record exist for given id", codeb);
        }
    }

    // Crea la bestia que le hemos pasado
    public beast createBeast(beast entity) {
        entity = repository.save(entity);
        return entity;
    }

    // Recibe una bestia y la actualizamos
    public beast UpdateBeast(beast entity) throws RecordNotFoundException {

        if (entity.getCodeb() != null) {
            Optional<beast> beast = repository.findById(entity.getCodeb());

            if (beast.isPresent()) {
                beast newEntity = beast.get();
                //newEntity.setCodeb(entity.getCodeb());
                newEntity.setNamebeast(entity.getNamebeast());
                newEntity.setType(entity.getType());
                newEntity.setLore(entity.getLore());
                newEntity.setImageb(entity.getImageb());
                newEntity.setPower(entity.getPower());               
                newEntity.setLife(entity.getLife());
                newEntity.setCreatorb(entity.getCreatorb());
                
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                throw new RecordNotFoundException("Beast not found", entity.getCodeb());
            }
        } else {
            throw new RecordNotFoundException("No id of beast given", 0l);
        }
    }

    // Borramos la bestia por id pasado
    public void deleteBeastById(Long code) throws RecordNotFoundException {
        Optional<beast> beast = repository.findById(code);
        System.out.println(beast);
        if (beast.isPresent()) {
            System.out.println(code);
            repository.deleteFromBeast(code);
        } else {
            throw new RecordNotFoundException("No beast record exist for given id", code);
        }
    }

    // Buscar por nombre de la bestia
    public List<beast> getBeastsByName(String namecharacter) {
        System.out.println(namecharacter);
        List<beast> beastList = repository.getByName(namecharacter);
        
        if (beastList.size() > 0) {
            return beastList;
        } else {
            return new ArrayList<beast>();
        }
    }
    
    // Buscar por nombre de la bestia, dado un usuario
    public List<beast> getBeastsByName(String namebeast, Long codeb) {
        System.out.println(namebeast);
        List<beast> beastList = repository.getByNameFromUser(namebeast, codeb);
        
        if (beastList.size() > 0) {
            return beastList;
        } else {
            return new ArrayList<beast>();
        }
    }
    
}
