package ma.medass.service.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class DemandeDto {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private Long id;
    private String nom;
    private String prenom;
    private String libelle;
    private String numeroPolice;

    private String numeroSinistre;
    private LocalDate dateDebutDemande;
    private LocalDate dateFinDemande;
    private String dateDebutDemandeStr;

    private String dateFinDemandeStr;
    private Long etatDemandeId;
    private Long resultatDemandeId;

    private Long compagnieId;
    private ReferenceDto compagnie;
    private ReferenceDto etatDemande;

    private ReferenceDto resultatDemande;

    private String mediateur = null;

    public DemandeDto() {
        // Empty constructor needed for Jackson.
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = StringUtils.isBlank(libelle) ? null : libelle.trim();
    }

    public String getNumeroPolice() {
        return this.numeroPolice;
    }

    public void setNumeroPolice(String numeroPolice) {
        this.numeroPolice = StringUtils.isBlank(numeroPolice) ? null : numeroPolice.trim();
        ;
    }

    public String getNumeroSinistre() {
        return this.numeroSinistre;
    }

    public void setNumeroSinistre(String numeroSinistre) {
        this.numeroSinistre = numeroSinistre;
    }

    public LocalDate getDateDebutDemande() {
        return this.dateDebutDemande;
    }

    public void setDateDebutDemande(LocalDate dateDebutDemande) {
        this.dateDebutDemande = dateDebutDemande;
        this.dateDebutDemandeStr = dateDebutDemande == null ? null : dateDebutDemande.format(DTF);
    }

    public LocalDate getDateFinDemande() {
        return this.dateFinDemande;
    }

    public void setDateFinDemande(LocalDate dateFinDemande) {
        this.dateFinDemande = dateFinDemande;
        this.dateFinDemandeStr = dateFinDemande == null ? null : dateFinDemande.format(DTF);
    }

    public Long getEtatDemandeId() {
        return this.etatDemandeId;
    }

    public void setEtatDemandeId(Long etatDemandeId) {
        this.etatDemandeId = etatDemandeId;
    }

    public Long getResultatDemandeId() {
        return this.resultatDemandeId;
    }

    public void setResultatDemandeId(Long resultatDemandeId) {
        this.resultatDemandeId = resultatDemandeId;
    }

    public Long getCompagnieId() {
        return this.compagnieId;
    }

    public void setCompagnieId(Long compagnieId) {
        this.compagnieId = compagnieId;
    }

    public String getDateDebutDemandeStr() {
        return this.dateDebutDemandeStr;
    }

    public void setDateDebutDemandeStr(String dateDebutDemandeStr) {
        this.dateDebutDemandeStr = dateDebutDemandeStr;
    }

    public String getDateFinDemandeStr() {
        return this.dateFinDemandeStr;
    }

    public void setDateFinDemandeStr(String dateFinDemandeStr) {
        this.dateFinDemandeStr = dateFinDemandeStr;
    }

    public ReferenceDto getCompagnie() {
        return this.compagnie;
    }

    public void setCompagnie(ReferenceDto compagnie) {
        this.compagnie = compagnie;
        if (compagnie != null && compagnie.getId() != null) {
            this.compagnieId = compagnie.getId();
        }
    }

    public ReferenceDto getEtatDemande() {
        return this.etatDemande;
    }

    public void setEtatDemande(ReferenceDto etatDemande) {
        this.etatDemande = etatDemande;
        if (etatDemande != null && etatDemande.getId() != null) {
            this.etatDemandeId = etatDemande.getId();
        }
    }

    public ReferenceDto getResultatDemande() {
        return this.resultatDemande;
    }

    public void setResultatDemande(ReferenceDto resultatDemande) {
        this.resultatDemande = resultatDemande;
        if (resultatDemande != null && resultatDemande.getId() != null) {
            this.resultatDemandeId = resultatDemande.getId();
        }
    }

    public String getMediateur() {
        return mediateur;
    }

    public void setMediateur(String mediateur) {
        this.mediateur = mediateur;
    }

}
