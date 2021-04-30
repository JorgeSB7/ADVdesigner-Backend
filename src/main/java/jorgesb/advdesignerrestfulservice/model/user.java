package jorgesb.advdesignerrestfulservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "users")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    


    @NotBlank
    @Column(name = "avatar", columnDefinition="TEXT")
    private String avatar;
    
    //_____________________________________________Personajes
    public List<character> getLcha() {
        return lcha;
    }

    public void setLcha(List<character> lcha) {
        this.lcha = lcha;
    }

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("creator")
    private List<character> lcha;

    public List<character> getCha() {
        return lcha;
    }

    public void setCha(List<character> cha) {
        if (cha == null) {
            cha = new ArrayList<character>();
        }
        this.lcha = cha;
        for (character character : cha) {
            character.setCreator(this);
        }
    }
    
    
    //_____________________________________________Bestiario
    
    public List<beast> getLbea() {
        return lbea;
    }

    public void setLbea(List<beast> lbea) {
        this.lbea = lbea;
    }

    @OneToMany(mappedBy = "creatorb", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("creatorb")
    private List<beast> lbea;

    public List<beast> getBea() {
        return lbea;
    }

    public void setBea(List<beast> bea) {
        if (bea == null) {
            bea = new ArrayList<beast>();
        }
        this.lbea = bea;
        for (beast beast : bea) {
            beast.setCreatorb(this);
        }
    }
    
    //_____________________________________________Magias
    
    public List<magic> getLmag() {
        return lmag;
    }

    public void setLmag(List<magic> lmag) {
        this.lmag = lmag;
    }

    @OneToMany(mappedBy = "creatorm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("creatorm")
    private List<magic> lmag;

    public List<magic> getMag() {
        return lmag;
    }

    public void setMag(List<magic> mag) {
        if (mag == null) {
            mag = new ArrayList<magic>();
        }
        this.lmag = mag;
        for (magic magic : mag) {
            magic.setCreatorm(this);
        }
    }
    
    //_________________________________________________getter/setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", name=" + name + ", email=" + email + ", avatar=" + avatar + '}';
    }

}
