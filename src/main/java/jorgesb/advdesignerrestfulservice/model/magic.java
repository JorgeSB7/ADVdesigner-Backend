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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "magics")
public class magic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codem;
    
    @NotBlank
    @Column(name = "namemagic")
    private String namemagic;
    
    @NotBlank
    @Column(name = "typem")
    private String typem;
    
    @Column(name = "lorem", columnDefinition="TEXT")
    private String lorem;

    // Añadimos el columnDefinition="TEXT" para poder guardar las imágenes en base64
    @Column(name = "imagem", columnDefinition="TEXT")
    private String imagem;

    @Column(name = "energy")
    private int energy;
    
    //_________________________________________________USER
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    //@LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "idcreatorm")
    @JsonIgnoreProperties("lmag")
    private user creatorm;

    public user getCreatorm() {
        return creatorm;
    }

    public void setCreatorm(user creatorm) {
        this.creatorm = creatorm;
        List<magic> list = this.creatorm.getMag();
        if (list == null) {
            list = new ArrayList();
        }
        if (!list.contains(this)) {
            list.add(this);
        }
    }

    //_________________________________________________getter/setter

    public Long getCodem() {
        return codem;
    }

    public void setCodem(Long codem) {
        this.codem = codem;
    }

    public String getNamemagic() {
        return namemagic;
    }

    public void setNamemagic(String namemagic) {
        this.namemagic = namemagic;
    }

    public String getTypem() {
        return typem;
    }

    public void setTypem(String typem) {
        this.typem = typem;
    }

    public String getLorem() {
        return lorem;
    }

    public void setLorem(String lorem) {
        this.lorem = lorem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
   
    //_________________________________________________toString

    @Override
    public String toString() {
        return "magic{" + "codem=" + codem + ", namemagic=" + namemagic + ", typem=" + typem + ", lorem=" + lorem + ", imagem=" + imagem + ", energy=" + energy + '}';
    }

    
}
