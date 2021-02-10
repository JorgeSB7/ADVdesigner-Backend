package jorgesb.advdesignerrestfulservice.services;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.character;
import jorgesb.advdesignerrestfulservice.repositories.characterRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class characterService {

    @Autowired
    characterRepository repository;

    // Devuelve todos los personajes que haya en la base de datos
    public List<character> getAllcharacters() {
        List<character> characterList = repository.findAll();

        if (characterList.size() > 0) {
            return characterList;
        } else {
            return new ArrayList<character>();
        }
    }

    // Devuelve el personaje por el id pasado
    public character getCharacterById(Long code) throws RecordNotFoundException {
        Optional<character> character = repository.findById(code);

        if (character.isPresent()) {
            return character.get();
        } else {
            // Si no lo encuentra lanza la excepcion
            throw new RecordNotFoundException("No character record exist for given id", code);
        }
    }

    // Crea el personaje que le hemos pasado
    public character createCharacter(character entity) {
        entity = repository.save(entity);
        return entity;
    }

    // Recibe un personaje y lo actualizamos
    public character UpdateCharacter(character entity) throws RecordNotFoundException {

        if (entity.getCode() != null) {
            Optional<character> character = repository.findById(entity.getCode());

            if (character.isPresent()) {
                character newEntity = character.get();
                //newEntity.setCode(entity.getCode());
                newEntity.setNamecharacter(entity.getNamecharacter());
                newEntity.setRace(entity.getRace());
                newEntity.setRolclass(entity.getRolclass());
                newEntity.setImage(entity.getImage());
                newEntity.setStrength(entity.getStrength());
                newEntity.setDexterity(entity.getDexterity());
                newEntity.setConstitution(entity.getConstitution());
                newEntity.setIntelligence(entity.getIntelligence());
                newEntity.setWisdom(entity.getWisdom());
                newEntity.setCharisma(entity.getCharisma());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Character not found", entity.getCode());
            }
        } else {
            throw new RecordNotFoundException("No id of character given", 0l);
        }
    }

    // Borramos el personaje por id pasado
    public void deleteCharacterById(Long code) throws RecordNotFoundException {
        Optional<character> character = repository.findById(code);
        System.out.println(character);
        if (character.isPresent()) {
            System.out.println(code);
            repository.deleteFromCharacter(code);
        } else {
            throw new RecordNotFoundException("No character record exist for given id", code);
        }
    }

    // Buscar por nombre de personaje
    public List<character> getCharactersByName(String namecharacter) {
        System.out.println(namecharacter);
        List<character> characterList = repository.getByName(namecharacter);
        
        if (characterList.size() > 0) {
            return characterList;
        } else {
            return new ArrayList<character>();
        }
    }
}
