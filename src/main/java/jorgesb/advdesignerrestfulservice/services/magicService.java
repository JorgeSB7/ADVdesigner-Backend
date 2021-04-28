package jorgesb.advdesignerrestfulservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.magic;
import jorgesb.advdesignerrestfulservice.repositories.magicRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class magicService {
    @Autowired
    magicRepository repository;
    
    // Devuelve todos los conjuros que haya en la base de datos
    public List<magic> getAllmagics() {
        List<magic> magicList = repository.findAll();

        if (magicList.size() > 0) {
            return magicList;
        } else {
            return new ArrayList<magic>();
        }
    }
    
    // Devuelve todos los conjuros de un usuario
    public List<magic> getAllMagicByIdUser(Long id) {
        List<magic> magicList = repository.getAllMagicByIdUser(id);

        if (magicList.size() > 0) {
            return magicList;
        } else {
            return new ArrayList<magic>();
        }
    }

    // Devuelve el conjuro por el id pasado
    public magic getMagicById(Long codem) throws RecordNotFoundException {
        Optional<magic> magic = repository.findById(codem);

        if (magic.isPresent()) {
            return magic.get();
        } else {
            // Si no lo encuentra lanza la excepcion
            throw new RecordNotFoundException("No magic record exist for given id", codem);
        }
    }

    // Crea el conjuro que le hemos pasado
    public magic createMagic(magic entity) {
        entity = repository.save(entity);
        return entity;
    }

    // Recibe un conjuro y lo actualizamos
    public magic UpdateMagic(magic entity) throws RecordNotFoundException {

        if (entity.getCodem() != null) {
            Optional<magic> magic = repository.findById(entity.getCodem());

            if (magic.isPresent()) {
                magic newEntity = magic.get();
                //newEntity.setCodem(entity.getCodem());
                newEntity.setNamemagic(entity.getNamemagic());
                newEntity.setTypem(entity.getTypem());
                newEntity.setLorem(entity.getLorem());
                newEntity.setImagem(entity.getImagem());
                newEntity.setEnergy(entity.getEnergy());               
                newEntity.setCreatorm(entity.getCreatorm());
                
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                throw new RecordNotFoundException("magic not found", entity.getCodem());
            }
        } else {
            throw new RecordNotFoundException("No id of magic given", 0l);
        }
    }

    // Borramos el conjuro por id pasado
    public void deleteMagicById(Long codem) throws RecordNotFoundException {
        Optional<magic> magic = repository.findById(codem);
        System.out.println(magic);
        if (magic.isPresent()) {
            System.out.println(codem);
            repository.deleteFromMagic(codem);
        } else {
            throw new RecordNotFoundException("No magic record exist for given id", codem);
        }
    }

    // Buscar por nombre de conjuro
    public List<magic> getMagicsByName(String namecharacter) {
        System.out.println(namecharacter);
        List<magic> magicList = repository.getByName(namecharacter);
        
        if (magicList.size() > 0) {
            return magicList;
        } else {
            return new ArrayList<magic>();
        }
    }
    
    // Buscar por nombre de conjuro, dado un usuario
    public List<magic> getMagicsByName(String namemagic, Long codem) {
        System.out.println(namemagic);
        List<magic> magicList = repository.getByNameFromUser(namemagic, codem);
        
        if (magicList.size() > 0) {
            return magicList;
        } else {
            return new ArrayList<magic>();
        }
    }
    
}
