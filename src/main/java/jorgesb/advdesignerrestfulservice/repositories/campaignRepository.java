package jorgesb.advdesignerrestfulservice.repositories;

import jorgesb.advdesignerrestfulservice.model.campaign;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface campaignRepository extends JpaRepository<campaign, Long> {
    
    @Query(value = "SELECT * FROM campaigns AS i WHERE i.namecampaign LIKE %?1%", nativeQuery = true)
    public List<campaign> getByName(String namecampaign);
    
    @Query(value = "DELETE FROM campaigns AS i WHERE i.code = ?1 RETURNING cdcam", nativeQuery = true)
    public Long deleteFromCampaign (Long cdcam);
}
