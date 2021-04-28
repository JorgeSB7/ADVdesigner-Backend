package jorgesb.advdesignerrestfulservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "beasts")
public class beast {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codeb;
    

    @NotBlank
    @Column(name = "namebeast")
    private String namebeast;
    
    @NotBlank
    @Column(name = "type")
    private String type;
    
    @Column(name = "lore", columnDefinition="TEXT")
    private String lore;

    // Añadimos el columnDefinition="TEXT" para poder guardar las imágenes en base64
    @Column(name = "imageb", columnDefinition="TEXT")
    private String imageb;

    @Column(name = "power")
    private int power;

    @Column(name = "life")
    private int life;
    
    //_________________________________________________USER
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcreatorb")
    @JsonIgnoreProperties("lbea")
    private user creatorb;

    public user getCreatorb() {
        return creatorb;
    }

    public void setCreatorb(user creatorb) {
        this.creatorb = creatorb;
        List<beast> list = this.creatorb.getBea();
        if (list == null) {
            list = new ArrayList();
        }
        if (!list.contains(this)) {
            list.add(this);
        }
    }
    
    //_________________________________________________getter/setter
    
    public Long getCodeb() {
        return codeb;
    }
    
    public void setCodeb(Long codeb) {
        this.codeb = codeb;
    }

    public String getNamebeast() {
        return namebeast;
    }

    public void setNamebeast(String namebeast) {
        this.namebeast = namebeast;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getImageb() {
        return imageb;
    }

    public void setImageb(String imageb) {
        this.imageb = imageb;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
    
    //_________________________________________________toString

    @Override
    public String toString() {
        return "beast{" + "codeb=" + codeb + ", namebeast=" + namebeast + ", type=" + type + ", lore=" + lore + ", imageb=" + imageb + ", power=" + power + ", life=" + life + '}';
    }

    
}
