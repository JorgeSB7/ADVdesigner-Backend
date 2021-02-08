package jorgesb.advdesignerrestfulservice.services;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.campaign;
import jorgesb.advdesignerrestfulservice.repositories.campaignRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class campaignService {

    @Autowired
    campaignRepository repository;

    // Devuelve todos los usuarios que haya en la base de datos
    public List<campaign> getAllcampaigns() {
        List<campaign> campaignList = repository.findAll();

        if (campaignList.size() > 0) {
            return campaignList;
        } else {
            return new ArrayList<campaign>();
        }
    }

    // Devuelve la campaña por el id pasado
    public campaign getCampaignById(Long cdcam) throws RecordNotFoundException {
        Optional<campaign> campaign = repository.findById(cdcam);

        if (campaign.isPresent()) {
            return campaign.get();
        } else {
            // Si no la encuentra lanza la excepcion
            throw new RecordNotFoundException("No campaign record exist for given id", cdcam);
        }
    }

    // Crea la campaña que le hemos pasado
    public campaign createCampaign(campaign entity) {
        entity = repository.save(entity);
        return entity;
    }

    // Recibe una campaña y la actualizamos
    public campaign UpdateCampaign(campaign entity) throws RecordNotFoundException {

        if (entity.getCdcam() != null) {
            Optional<campaign> campaign = repository.findById(entity.getCdcam());

            if (campaign.isPresent()) {
                campaign newEntity = campaign.get();
                //newEntity.setCdcam(entity.getCdcam());
                newEntity.setNamecampaign(entity.getNamecampaign());
                newEntity.setPicture(entity.getPicture());
                newEntity.setDescription(entity.getDescription());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                throw new RecordNotFoundException("Campaign not found", entity.getCdcam());
            }
        } else {
            throw new RecordNotFoundException("No id of campaign given", 0l);
        }
    }

    // Borramos la campaña por id pasado
    public void deleteCampaignById(Long cdcam) throws RecordNotFoundException {
        Optional<campaign> campaign = repository.findById(cdcam);

        if (campaign.isPresent()) {
            repository.deleteById(cdcam);
        } else {
            throw new RecordNotFoundException("No campaign record exist for given id", cdcam);
        }
    }

    // Buscar por nombre de campaña
    public List<campaign> getCampaignsByName(String namecampaign) {
        List<campaign> campaignList = repository.getByName(namecampaign);

        if (campaignList.size() > 0) {
            return campaignList;
        } else {
            return new ArrayList<campaign>();
        }
    }
}
