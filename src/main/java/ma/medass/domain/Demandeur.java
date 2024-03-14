package ma.medass.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Demandeur.
 */
@Entity
@Table(name = "demandeur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Demandeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "email")
    private String email;

    @ManyToOne
    private TypeDemandeur typeDemandeur;

    @ManyToOne
    private Civilite civilite;

    @ManyToOne
    private Ville ville;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Demandeur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

//    public Demandeur libelle(String libelle) {
//        this.setLibelle(libelle);
//        return this;
//    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPrenom() {
        return this.prenom;
    }

//    public Demandeur prenom(String prenom) {
//        this.setPrenom(prenom);
//        return this;
//    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return this.adresse;
    }

//    public Demandeur adresse(String adresse) {
//        this.setAdresse(adresse);
//        return this;
//    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

//    public Demandeur telephone(String telephone) {
//        this.setTelephone(telephone);
//        return this;
//    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public Demandeur email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TypeDemandeur getTypeDemandeur() {
        return this.typeDemandeur;
    }

    public void setTypeDemandeur(TypeDemandeur typeDemandeur) {
        this.typeDemandeur = typeDemandeur;
    }

    public Demandeur typeDemandeur(TypeDemandeur typeDemandeur) {
        this.setTypeDemandeur(typeDemandeur);
        return this;
    }

    public Civilite getCivilite() {
        return this.civilite;
    }

    public void setCivilite(Civilite civilite) {
        this.civilite = civilite;
    }

    public Demandeur civilite(Civilite civilite) {
        this.setCivilite(civilite);
        return this;
    }

    public Ville getVille() {
        return this.ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Demandeur ville(Ville ville) {
        this.setVille(ville);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demandeur)) {
            return false;
        }
        return id != null && id.equals(((Demandeur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demandeur{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }

    @Transient
    private String _type = "demandeur";

    public String get_type() {
        return this._type;
    }
}
