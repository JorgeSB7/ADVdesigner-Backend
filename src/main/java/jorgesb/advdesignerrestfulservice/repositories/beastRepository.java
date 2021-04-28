package jorgesb.advdesignerrestfulservice.repositories;

import java.util.List;
import jorgesb.advdesignerrestfulservice.model.beast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface beastRepository extends JpaRepository<beast, Long> {
    @Query(value = "SELECT * FROM beasts AS i WHERE i.idcreatorb=?1", nativeQuery = true)
    public List<beast> getAllBeastByIdUser(Long codeb);

    @Query(value = "SELECT * FROM beasts AS i WHERE i.namebeast LIKE %?1%", nativeQuery = true)
    public List<beast> getByName(String namebeast);

    @Query(value = "SELECT * FROM beasts AS i WHERE i.beasts LIKE %?1% AND i.idcreatorb=?2", nativeQuery = true)
    public List<beast> getByNameFromUser(String namebeast, Long codeb);

    @Query(value = "DELETE FROM beasts AS i WHERE i.codeb = ?1 RETURNING codeb", nativeQuery = true)
    public Long deleteFromBeast(Long codeb);
}
