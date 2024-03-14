package ma.medass.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class DemandeLight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "date_etat")
    private LocalDate dateEtat;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "numero_sinistre")
    private String numeroSinistre;

    @Column(name = "numero_police")
    private String numeroPolice;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Column(name = "date_etude")
    private LocalDate dateEtude;

    @Column(name = "date_traitement")
    private LocalDate dateTraitement;

    @ManyToOne
    private CompagnieLight compagnie;

    @ManyToOne
    @JsonIgnoreProperties(value = { "etatDemande", "demande" }, allowSetters = true)
    private EtatDemandeHist etatDemandeHist;

    @ManyToOne
    private ResultatDemande resultatDemande;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    @Transient
    private String _type = "demande";

    public Long getId() {
        return this.id;
    }

    public DemandeLight id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEtat() {
        return this.dateEtat;
    }

    public DemandeLight dateEtat(LocalDate dateEtat) {
        this.setDateEtat(dateEtat);
        return this;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public DemandeLight libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

    public DemandeLight numeroSinistre(String numeroSinistre) {
        this.setNumeroSinistre(numeroSinistre);
        return this;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public String getNumeroPolice() {
        return this.numeroPolice;
    }

    public DemandeLight numeroPolice(String numeroPolice) {
        this.setNumeroPolice(numeroPolice);
        return this;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public LocalDate getDateDemande() {
        return this.dateDemande;
    }

    public DemandeLight dateDemande(LocalDate dateDemande) {
        this.setDateDemande(dateDemande);
        return this;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public CompagnieLight getCompagnie() {
        return this.compagnie;
    }

    public void setCompagnie(CompagnieLight compagnie) {
        this.compagnie = compagnie;
    }

    public DemandeLight compagnie(CompagnieLight compagnie) {
        this.setCompagnie(compagnie);
        return this;
    }

    public EtatDemandeHist getEtatDemandeHist() {
        return this.etatDemandeHist;
    }

    public void setEtatDemandeHist(EtatDemandeHist etatDemandeHist) {
        this.etatDemandeHist = etatDemandeHist;
    }

    public DemandeLight etatDemandeHist(EtatDemandeHist etatDemandeHist) {
        this.setEtatDemandeHist(etatDemandeHist);
        return this;
    }

    public ResultatDemande getResultatDemande() {
        return this.resultatDemande;
    }

    public void setResultatDemande(ResultatDemande resultatDemande) {
        this.resultatDemande = resultatDemande;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    public DemandeLight resultatDemande(ResultatDemande resultatDemande) {
        this.setResultatDemande(resultatDemande);
        return this;
    }

    public LocalDate getDateEtude() {
        return this.dateEtude;
    }

    public DemandeLight dateEtude(LocalDate dateEtude) {
        this.setDateEtude(dateEtude);
        return this;
    }

    public void setDateEtude(LocalDate dateEtude) {
        this.dateEtude = dateEtude;
    }

    public LocalDate getDateTraitement() {
        return this.dateTraitement;
    }

    public DemandeLight dateTraitement(LocalDate dateTraitement) {
        this.setDateTraitement(dateTraitement);
        return this;
    }

    public void setDateTraitement(LocalDate dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandeLight)) {
            return false;
        }
        return id != null && id.equals(((DemandeLight) o).id);
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DemandeLight{" +
                "id=" + getId() +
                ", dateEtat='" + getDateEtat() + "'" +
                ", libelle='" + getLibelle() + "'" +
                ", numeroSinistre='" + getNumeroSinistre() + "'" +
                ", numeroPolice='" + getNumeroPolice() + "'" +
                ", dateDemande='" + getDateDemande() + "'" +
                "}";
    }

    public String get_type() {
        return this._type;
    }
}
