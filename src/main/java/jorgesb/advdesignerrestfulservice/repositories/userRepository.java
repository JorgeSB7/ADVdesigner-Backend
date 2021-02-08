package jorgesb.advdesignerrestfulservice.repositories;

import jorgesb.advdesignerrestfulservice.model.user;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user, Long> {

    @Query(value = "SELECT * FROM users AS i WHERE i.name LIKE %?1%", nativeQuery = true)
    public List<user> getByName(String name);
}
