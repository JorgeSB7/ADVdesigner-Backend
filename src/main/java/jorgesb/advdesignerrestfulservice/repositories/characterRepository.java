package jorgesb.advdesignerrestfulservice.repositories;

import jorgesb.advdesignerrestfulservice.model.character;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface characterRepository extends JpaRepository<character, Long> {
    
    @Query(value = "SELECT * FROM users AS i WHERE i.namecharacter LIKE %?1%", nativeQuery = true)
    public List<character> getByName(String namecharacter);
}
