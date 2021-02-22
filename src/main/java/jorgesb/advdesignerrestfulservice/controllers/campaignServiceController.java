package jorgesb.advdesignerrestfulservice.controllers;

import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.campaign;
import jorgesb.advdesignerrestfulservice.services.campaignService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/campaign")
public class campaignServiceController {
    @Autowired
    campaignService service;
    
    @GetMapping
    public ResponseEntity<List<campaign>> getAllcampaigns() {
        List<campaign> list = service.getAllcampaigns();

        return new ResponseEntity<List<campaign>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{cdcam}")
    public ResponseEntity<campaign> getCampaignById(@PathVariable("cdcam") Long cdcam)
            throws RecordNotFoundException {
        campaign entity = service.getCampaignById(cdcam);

        return new ResponseEntity<campaign>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    
    @GetMapping("/search/{namecampaign}")
    public ResponseEntity<List<campaign>> getCampaignsByName(@PathVariable("namecampaign") String namecampaign) {
        List<campaign> list = service.getCampaignsByName(namecampaign);

        return new ResponseEntity<List<campaign>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<campaign> createCampaign(@Valid @RequestBody campaign myCampaign) {
        campaign created = service.createCampaign(myCampaign);
        return new ResponseEntity<campaign>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<campaign> UpdateCampaign(@Valid @RequestBody campaign myCampaign)
            throws RecordNotFoundException {
        campaign updated = service.UpdateCampaign(myCampaign);
        return new ResponseEntity<campaign>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{cdcam}")
    public HttpStatus deleteCampaignById(@PathVariable("cdcam") Long cdcam)
            throws RecordNotFoundException {
        service.deleteCampaignById(cdcam);
        return HttpStatus.ACCEPTED;
    }

}