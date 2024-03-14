package ma.medass.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A EtatDemandeHist.
 */
@Entity
@Table(name = "etat_demande_hist")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EtatDemandeHist implements Serializable {

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
    @Column(name = "date_etat", nullable = false)
    private LocalDate dateEtat;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "commentaire")
    private String commentaire;

    @ManyToOne
    private EtatDemande etatDemande;

    @Transient
    private List<MediaLight> listeMedia;

    @ManyToOne
    @JsonIgnoreProperties(
        value = { "compagnie", "demandeur", "etatDemandeHist", "resultatDemande", "branche", "natureLitige", "natureSinistre", "objetLitige" },
        allowSetters = true)
    private Demande demande;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EtatDemandeHist id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public EtatDemandeHist libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateEtat() {
        return this.dateEtat;
    }

    public EtatDemandeHist dateEtat(LocalDate dateEtat) {
        this.setDateEtat(dateEtat);
        return this;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public EtatDemandeHist commentaire(String commentaire) {
        this.setCommentaire(commentaire);
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public EtatDemande getEtatDemande() {
        return this.etatDemande;
    }

    public void setEtatDemande(EtatDemande etatDemande) {
        this.etatDemande = etatDemande;
    }

    public EtatDemandeHist etatDemande(EtatDemande etatDemande) {
        this.setEtatDemande(etatDemande);
        return this;
    }

    public Demande getDemande() {
        return this.demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }

    public EtatDemandeHist demande(Demande demande) {
        this.setDemande(demande);
        return this;
    }

    public List<MediaLight> getListeMedia() {
        return this.listeMedia;
    }

    public void setListeMedia(List<MediaLight> listeMedia) {
        this.listeMedia = listeMedia;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtatDemandeHist)) {
            return false;
        }
        return id != null && id.equals(((EtatDemandeHist) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EtatDemandeHist{" +
                "id=" + getId() +
                ", libelle='" + getLibelle() + "'" +
                ", dateEtat='" + getDateEtat() + "'" +
                ", commentaire='" + getCommentaire() + "'" +
                "}";
    }

    @Transient
    private String _type = "etatDemandeHist";

    public String get_type() {
        return this._type;
    }
}
