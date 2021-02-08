package jorgesb.advdesignerrestfulservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "campaigns")
public class campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdcam;

    @NotBlank
    @Column(name = "namecampaign")
    private String namecampaign;

    @NotBlank
    @Column(name = "picture")
    private String picture;

    @NotBlank
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "campaigns")
    @JsonIgnoreProperties("campaigns")
    private List<character> cha;

    public List<character> getCha() {
        return cha;
    }

    public void setCam(List<character> cha) {
        this.cha = cha;
        for (character character : cha) {
            List<campaign> list = character.getCampaigns();
            if (list == null) {
                list = new ArrayList<campaign>();
            }
            if (!list.contains(this)) {
                list.add(this);
            }
        }
    }

    public Long getCdcam() {
        return cdcam;
    }

    public void setCdcam(Long cdcam) {
        this.cdcam = cdcam;
    }

    public String getNamecampaign() {
        return namecampaign;
    }

    public void setNamecampaign(String namecampaign) {
        this.namecampaign = namecampaign;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "campaign{" + "cdcam=" + cdcam + ", namecampaign=" + namecampaign + ", picture=" + picture + ", description=" + description + '}';
    }

}
