package ma.medass.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Demande implements Serializable {

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

    @Column(name = "date_sinistre")
    private LocalDate dateSinistre;

    @Column(name = "date_souscription")
    private LocalDate dateSouscription;

    @Column(name = "montant_reclamation", precision = 21, scale = 2)
    private BigDecimal montantReclamation;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description_litige")
    private String descriptionLitige;

    @Column(name = "souscripteur")
    private String souscripteur;

    @Column(name = "assure")
    private String assure;

    @Column(name = "en_cours_acaps")
    private Boolean enCoursAcaps;

    @Column(name = "en_cours_autre_juridiction")
    private Boolean enCoursAutreJuridiction;

    @Column(name = "date_etude")
    private LocalDate dateEtude;

    @Column(name = "date_traitement")
    private LocalDate dateTraitement;

    @ManyToOne
    private Compagnie compagnie;

    @ManyToOne
    private Demandeur demandeur;

    @ManyToOne
    @JsonIgnoreProperties(value = { "demande" }, allowSetters = true)
    private EtatDemandeHist etatDemandeHist;

    @ManyToOne
    private ResultatDemande resultatDemande;

    @ManyToOne
    private Branche branche;

    @ManyToOne
    private NatureLitige natureLitige;

    @ManyToOne
    private NatureSinistre natureSinistre;

    @ManyToOne
    private ObjetLitige objetLitige;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    @Transient
    private String _type = "demande";

    public Long getId() {
        return this.id;
    }

    public Demande id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEtat() {
        return this.dateEtat;
    }

    public Demande dateEtat(LocalDate dateEtat) {
        this.setDateEtat(dateEtat);
        return this;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Demande libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

//    public Demande numeroSinistre(String numeroSinistre) {
//        this.setNumeroSinistre(numeroSinistre);
//        return this;
//    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public String getNumeroPolice() {
        return this.numeroPolice;
    }

//    public Demande numeroPolice(String numeroPolice) {
//        this.setNumeroPolice(numeroPolice);
//        return this;
//    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = numeroPolice;
    }

    public LocalDate getDateDemande() {
        return this.dateDemande;
    }

    public Demande dateDemande(LocalDate dateDemande) {
        this.setDateDemande(dateDemande);
        return this;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDate getDateSinistre() {
        return this.dateSinistre;
    }

    public Demande dateSinistre(LocalDate dateSinistre) {
        this.setDateSinistre(dateSinistre);
        return this;
    }

    public void setDateSinistre(LocalDate dateSinistre) {
        this.dateSinistre = dateSinistre;
    }

    public LocalDate getDateSouscription() {
        return this.dateSouscription;
    }

    public Demande dateSouscription(LocalDate dateSouscription) {
        this.setDateSouscription(dateSouscription);
        return this;
    }

    public void setDateSouscription(LocalDate dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public BigDecimal getMontantReclamation() {
        return this.montantReclamation;
    }

    public Demande montantReclamation(BigDecimal montantReclamation) {
        this.setMontantReclamation(montantReclamation);
        return this;
    }

    public void setMontantReclamation(BigDecimal montantReclamation) {
        this.montantReclamation = montantReclamation;
    }

    public String getDescriptionLitige() {
        return this.descriptionLitige;
    }

//    public Demande descriptionLitige(String descriptionLitige) {
//        this.setDescriptionLitige(descriptionLitige);
//        return this;
//    }

    public void setDescriptionLitige(String descriptionLitige) {
        this.descriptionLitige = descriptionLitige;
    }

    public String getSouscripteur() {
        return this.souscripteur;
    }

//    public Demande souscripteur(String souscripteur) {
//        this.setSouscripteur(souscripteur);
//        return this;
//    }

    public void setSouscripteur(String souscripteur) {
        this.souscripteur = souscripteur;
    }

    public String getAssure() {
        return this.assure;
    }

//    public Demande assure(String assure) {
//        this.setAssure(assure);
//        return this;
//    }

    public void setAssure(String assure) {
        this.assure = assure;
    }

    public Boolean getEnCoursAcaps() {
        return this.enCoursAcaps;
    }

//    public Demande enCoursAcaps(Boolean enCoursAcaps) {
//        this.setEnCoursAcaps(enCoursAcaps);
//        return this;
//    }

    public void setEnCoursAcaps(Boolean enCoursAcaps) {
        this.enCoursAcaps = enCoursAcaps;
    }

    public Boolean getEnCoursAutreJuridiction() {
        return this.enCoursAutreJuridiction;
    }

    public Demande enCoursAutreJuridiction(Boolean enCoursAutreJuridiction) {
        this.setEnCoursAutreJuridiction(enCoursAutreJuridiction);
        return this;
    }

    public void setEnCoursAutreJuridiction(Boolean enCoursAutreJuridiction) {
        this.enCoursAutreJuridiction = enCoursAutreJuridiction;
    }

    public LocalDate getDateEtude() {
        return this.dateEtude;
    }

    public Demande dateEtude(LocalDate dateEtude) {
        this.setDateEtude(dateEtude);
        return this;
    }

    public void setDateEtude(LocalDate dateEtude) {
        this.dateEtude = dateEtude;
    }

    public LocalDate getDateTraitement() {
        return this.dateTraitement;
    }

    public Demande dateTraitement(LocalDate dateTraitement) {
        this.setDateTraitement(dateTraitement);
        return this;
    }

    public void setDateTraitement(LocalDate dateTraitement) {
        this.dateTraitement = dateTraitement;
    }

    public Compagnie getCompagnie() {
        return this.compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public Demande compagnie(Compagnie compagnie) {
        this.setCompagnie(compagnie);
        return this;
    }

    public Demandeur getDemandeur() {
        return this.demandeur;
    }

    public void setDemandeur(Demandeur demandeur) {
        this.demandeur = demandeur;
    }

    public Demande demandeur(Demandeur demandeur) {
        this.setDemandeur(demandeur);
        return this;
    }

    public EtatDemandeHist getEtatDemandeHist() {
        return this.etatDemandeHist;
    }

    public void setEtatDemandeHist(EtatDemandeHist etatDemandeHist) {
        this.etatDemandeHist = etatDemandeHist;
    }

    public Demande etatDemandeHist(EtatDemandeHist etatDemandeHist) {
        this.setEtatDemandeHist(etatDemandeHist);
        return this;
    }

    public ResultatDemande getResultatDemande() {
        return this.resultatDemande;
    }

    public void setResultatDemande(ResultatDemande resultatDemande) {
        this.resultatDemande = resultatDemande;
    }

    public Demande resultatDemande(ResultatDemande resultatDemande) {
        this.setResultatDemande(resultatDemande);
        return this;
    }

    public Branche getBranche() {
        return this.branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

    public Demande branche(Branche branche) {
        this.setBranche(branche);
        return this;
    }

    public NatureLitige getNatureLitige() {
        return this.natureLitige;
    }

    public void setNatureLitige(NatureLitige natureLitige) {
        this.natureLitige = natureLitige;
    }

//    public Demande natureLitige(NatureLitige natureLitige) {
//        this.setNatureLitige(natureLitige);
//        return this;
//    }

    public NatureSinistre getNatureSinistre() {
        return this.natureSinistre;
    }

    public void setNatureSinistre(NatureSinistre natureSinistre) {
        this.natureSinistre = natureSinistre;
    }

//    public Demande natureSinistre(NatureSinistre natureSinistre) {
//        this.setNatureSinistre(natureSinistre);
//        return this;
//    }

    public ObjetLitige getObjetLitige() {
        return this.objetLitige;
    }

    public void setObjetLitige(ObjetLitige objetLitige) {
        this.objetLitige = objetLitige;
    }

    public Demande objetLitige(ObjetLitige objetLitige) {
        this.setObjetLitige(objetLitige);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demande)) {
            return false;
        }
        return id != null && id.equals(((Demande) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Demande{" +
                "id=" + getId() +
                ", dateEtat='" + getDateEtat() + "'" +
                ", libelle='" + getLibelle() + "'" +
                ", numeroSinistre='" + getNumeroSinistre() + "'" +
                ", numeroPolice='" + getNumeroPolice() + "'" +
                ", dateDemande='" + getDateDemande() + "'" +
                ", dateSinistre='" + getDateSinistre() + "'" +
                ", dateSouscription='" + getDateSouscription() + "'" +
                ", montantReclamation=" + getMontantReclamation() +
                ", descriptionLitige='" + getDescriptionLitige() + "'" +
                ", souscripteur='" + getSouscripteur() + "'" +
                ", assure='" + getAssure() + "'" +
                ", enCoursAcaps='" + getEnCoursAcaps() + "'" +
                ", enCoursAutreJuridiction='" + getEnCoursAutreJuridiction() + "'" +
                "}";
    }

    public String get_type() {
        return this._type;
    }
}
