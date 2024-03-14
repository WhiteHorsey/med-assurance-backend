package ma.medass.service.dto;

public class DemandeParChampParAnneeDto {

    private String champ;

    private String annee;
    private Long nombre;

    public DemandeParChampParAnneeDto() {
        // Empty constructor needed for Jackson.
    }

    public DemandeParChampParAnneeDto(Long nombre, String annee , String champ) {
        this.nombre = nombre;
        this.champ = champ;
        this.annee = annee;
    }
 

    public String getChamp() {
        return this.champ;
    }

    public void setChamp(String champ) {
        this.champ = champ;
    }
    
    public String getAnnee() {
        return this.annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public Long getNombre() {
        return this.nombre;
    }

    public void setNombre(Long nombre) {
        this.nombre = nombre;
    }

    
}
