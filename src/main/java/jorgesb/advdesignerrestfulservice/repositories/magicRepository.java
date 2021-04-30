package jorgesb.advdesignerrestfulservice.repositories;

import java.util.List;
import jorgesb.advdesignerrestfulservice.model.magic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface magicRepository extends JpaRepository<magic, Long> {
    
    @Query(value = "SELECT * FROM magics AS i WHERE i.idcreatorm=?1", nativeQuery = true)
    public List<magic> getAllMagicByIdUser(Long codem);

    @Query(value = "SELECT * FROM magics AS i WHERE i.namemagic LIKE %?1%", nativeQuery = true)
    public List<magic> getByName(String namemagic);

    @Query(value = "SELECT * FROM magics AS i WHERE i.namemagic LIKE %?1% AND i.idcreatorm=?2", nativeQuery = true)
    public List<magic> getByNameFromUser(String namemagic, Long codem);

    @Query(value = "DELETE FROM magics AS i WHERE i.codem = ?1 RETURNING codem", nativeQuery = true)
    public Long deleteFromMagic(Long codem);
}
