package jorgesb.advdesignerrestfulservice.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jorgesb.advdesignerrestfulservice.exceptions.RecordNotFoundException;
import jorgesb.advdesignerrestfulservice.model.campaign;
import jorgesb.advdesignerrestfulservice.services.campaignService;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/campaign")
public class campaignServiceController {
    @Autowired
    campaignService service;
    
    @ApiOperation(value = "getAllcampaigns", notes = "Esta funcion devolvera una lista campañas y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = campaign.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping
    public ResponseEntity<List<campaign>> getAllcampaigns() {
        List<campaign> list = service.getAllcampaigns();

        return new ResponseEntity<List<campaign>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "getCampaignById", notes = "Esta funcion devolvera un campaña por cdcam pasado y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = campaign.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/{cdcam}")
    public ResponseEntity<campaign> getCampaignById(@PathVariable("cdcam") Long cdcam)
            throws RecordNotFoundException {
        campaign entity = service.getCampaignById(cdcam);

        return new ResponseEntity<campaign>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "getCampaignsByName", notes = "Esta funcion devolvera una lista de campañas por nombre pasado y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = campaign.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @GetMapping("/search/{namecampaign}")
    public ResponseEntity<List<campaign>> getCampaignsByName(@PathVariable("namecampaign") String namecampaign) {
        List<campaign> list = service.getCampaignsByName(namecampaign);

        return new ResponseEntity<List<campaign>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "createCampaign", notes = "Esta funcion creara una campaña y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = campaign.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PostMapping
    public ResponseEntity<campaign> createCampaign(@Valid @RequestBody campaign myCampaign) {
        campaign created = service.createCampaign(myCampaign);
        return new ResponseEntity<campaign>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "UpdateCampaign", notes = "Esta funcion actualizara una campaña y dara una respuesta HTTP completa")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = campaign.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @PutMapping
    public ResponseEntity<campaign> UpdateCampaign(@Valid @RequestBody campaign myCampaign)
            throws RecordNotFoundException {
        campaign updated = service.UpdateCampaign(myCampaign);
        return new ResponseEntity<campaign>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "deleteCampaignById", notes = "Esta funcion borrara una campaña y dara una respuesta HTTP status")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = campaign.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 500, message = "Error inesperado del sistema")})
    @DeleteMapping("/{cdcam}")
    public HttpStatus deleteCampaignById(@PathVariable("cdcam") Long cdcam)
            throws RecordNotFoundException {
        service.deleteCampaignById(cdcam);
        return HttpStatus.ACCEPTED;
    }

}