package ma.medass.service.dto;

public class ReferenceDto {

    private Long id;
    private String libelle;


    public ReferenceDto() {
        // Empty constructor needed for Jackson.
    }

    public ReferenceDto(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

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


    
}
