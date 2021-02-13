package jorgesb.advdesignerrestfulservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "characters")
public class character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @NotBlank
    @Column(name = "namecharacter")
    private String namecharacter;

    @NotBlank
    @Column(name = "race")
    private String race;

    @NotBlank
    @Column(name = "rolclass")
    private String rolclass;
    
    // Añadimos el columnDefinition="TEXT" para poder guardar las imágenes en base64
    @NotBlank
    @Column(name = "image", columnDefinition="TEXT")
    private String image;

    //@NotBlank
    @Column(name = "strength")
    private int strength;

    //@NotBlank
    @Column(name = "dexterity")
    private int dexterity;

    //@NotBlank
    @Column(name = "constitution")
    private int constitution;

    //@NotBlank
    @Column(name = "intelligence")
    private int intelligence;

    //@NotBlank
    @Column(name = "wisdom")
    private int wisdom;

    //@NotBlank
    @Column(name = "charisma")
    private int charisma;

    @Column(name = "listcharacter")
    @JoinTable(
            name = "listcharacter",
            joinColumns = @JoinColumn(name = "FK_character", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_campaign", nullable = false)
    )
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("cha")
    private List<campaign> campaigns;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcreator")
    @JsonIgnoreProperties("lcha")
    private user creator;

    public user getCreator() {
        return creator;
    }

    public void setCreator(user creator) {
        this.creator = creator;
        List<character> list = this.creator.getCha();
        if (list == null) {
            list = new ArrayList();
        }
        if (!list.contains(this)) {
            list.add(this);
        }
    }

    public List<campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<campaign> campaigns) {
        if (campaigns == null) {
            campaigns = new ArrayList<campaign>();
        }
        this.campaigns = campaigns;
        for (campaign campaign : campaigns) {
            List<character> list = campaign.getCha();
            if (list == null) {
                list = new ArrayList<character>();
            }
            if (!list.contains(this)) {
                list.add(this);
            }
        }
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getNamecharacter() {
        return namecharacter;
    }

    public void setNamecharacter(String namecharacter) {
        this.namecharacter = namecharacter;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getRolclass() {
        return rolclass;
    }

    public void setRolclass(String rolclass) {
        this.rolclass = rolclass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    @Override
    public String toString() {
        return "character{" + "code=" + code + ", namecharacter=" + namecharacter + ", race=" + race + ", rolclass=" + rolclass + ", image=" + image + ", strength=" + strength + ", dexterity=" + dexterity + ", constitution=" + constitution + ", intelligence=" + intelligence + ", wisdom=" + wisdom + ", charisma=" + charisma + '}';
    }

}
