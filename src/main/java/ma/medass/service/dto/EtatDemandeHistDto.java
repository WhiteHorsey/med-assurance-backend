package ma.medass.service.dto;

import java.time.LocalDate;

public class EtatDemandeHistDto {

    private Long id;
    private String libelle;
    private LocalDate dateEtat;
    private String commentaire;
    private ReferenceDto etatDemande;
    private ReferenceDto resultatDemande;

    private DemandeDto demande;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateEtat() {
        return this.dateEtat;
    }

    public void setDateEtat(LocalDate dateEtat) {
        this.dateEtat = dateEtat;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ReferenceDto getEtatDemande() {
        return this.etatDemande;
    }

    public void setEtatDemande(ReferenceDto etatDemande) {
        this.etatDemande = etatDemande;
    }

    public ReferenceDto getResultatDemande() {
        return this.resultatDemande;
    }

    public void setResultatDemande(ReferenceDto resultatDemande) {
        this.resultatDemande = resultatDemande;
    }

    public DemandeDto getDemande() {
        return this.demande;
    }

    public void setDemande(DemandeDto demande) {
        this.demande = demande;
    }

}
