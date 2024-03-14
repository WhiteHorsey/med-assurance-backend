package ma.medass.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.medass.domain.DemandeLight;
import ma.medass.service.dto.DemandeDto;

@Repository
public interface DemandeLightRepository extends JpaRepository<DemandeLight, Long> {

    @Query("select distinct(d) from Demande d where " +
        "( :#{#query.libelle} is null OR d.libelle=:#{#query.libelle} ) " +
        "AND ( :#{#query.numeroPolice} is null OR d.numeroPolice=:#{#query.numeroPolice} ) " +
        "AND ( :#{#query.numeroSinistre} is null OR d.numeroSinistre=:#{#query.numeroSinistre} ) " +
        "AND ( :#{#query.compagnieId} is null OR d.compagnie.id=:#{#query.compagnieId} ) " +
        "AND ( :#{#query.etatDemandeId} is null OR d.etatDemandeHist.etatDemande.id=:#{#query.etatDemandeId} ) " +
        "AND ( :#{#query.resultatDemandeId} is null OR d.resultatDemande.id=:#{#query.resultatDemandeId})  " +
        "AND ( :#{#query.mediateur} is not null OR d.etatDemandeHist.etatDemande.id= 4 OR d.dateTraitement IS NOT NULL) " +
        "ORDER BY d.dateEtat ASC")
    Page<DemandeLight> filter(@Param("query") DemandeDto demandeDto, Pageable pageable);

    @Query("select distinct(d) from Demande d where " +
        "( :#{#query.libelle} is null OR d.libelle=:#{#query.libelle} ) " +
        "AND ( :#{#query.numeroPolice} is null OR d.numeroPolice=:#{#query.numeroPolice} ) " +
        "AND ( :#{#query.numeroSinistre} is null OR d.numeroSinistre=:#{#query.numeroSinistre} ) " +
        "AND ( CAST(:#{#query.dateDebutDemande} as date)  is null OR d.dateDemande>=  :#{#query.dateDebutDemande}    ) "
        +
        "AND ( :#{#query.compagnieId} is null OR d.compagnie.id=:#{#query.compagnieId} ) " +
        "AND ( :#{#query.etatDemandeId} is null OR d.etatDemandeHist.etatDemande.id=:#{#query.etatDemandeId} ) " +
        "AND ( :#{#query.resultatDemandeId} is null OR d.resultatDemande.id=:#{#query.resultatDemandeId})  " +
        "AND ( :#{#query.mediateur} is not null OR d.etatDemandeHist.etatDemande.id= 4 OR d.dateTraitement IS NOT NULL)"+
        "ORDER BY d.dateEtat ASC")
    Page<DemandeLight> filterDD(@Param("query") DemandeDto demandeDto, Pageable pageable);

    @Query("select distinct(d) from Demande d where " +
        "( :#{#query.libelle} is null OR d.libelle=:#{#query.libelle} ) " +
        "AND ( :#{#query.numeroPolice} is null OR d.numeroPolice=:#{#query.numeroPolice} ) " +
        "AND ( :#{#query.numeroSinistre} is null OR d.numeroSinistre=:#{#query.numeroSinistre} ) " +
        "AND ( CAST(:#{#query.dateFinDemande} as date)  is null OR d.dateDemande<=   :#{#query.dateFinDemande}    ) "
        +
        "AND ( :#{#query.compagnieId} is null OR d.compagnie.id=:#{#query.compagnieId} ) " +
        "AND ( :#{#query.etatDemandeId} is null OR d.etatDemandeHist.etatDemande.id=:#{#query.etatDemandeId} ) " +
        "AND ( :#{#query.resultatDemandeId} is null OR d.resultatDemande.id=:#{#query.resultatDemandeId})  " +
        "AND ( :#{#query.mediateur} is not null OR d.etatDemandeHist.etatDemande.id= 4 OR d.dateTraitement is not null)"+
        "ORDER BY d.dateEtat ASC")
    Page<DemandeLight> filterDF(@Param("query") DemandeDto demandeDto, Pageable pageable);

    @Query("select distinct(d) from Demande d where " +
        "( :#{#query.libelle} is null OR d.libelle=:#{#query.libelle} ) " +
        "AND ( :#{#query.numeroPolice} is null OR d.numeroPolice=:#{#query.numeroPolice} ) " +
        "AND ( :#{#query.numeroSinistre} is null OR d.numeroSinistre=:#{#query.numeroSinistre} ) " +
        "AND ( CAST(:#{#query.dateDebutDemande} as date)  is null OR d.dateDemande>=  :#{#query.dateDebutDemande}    ) "
        +
        "AND ( CAST(:#{#query.dateFinDemande} as date)  is null OR d.dateDemande<=   :#{#query.dateFinDemande}    ) "
        +
        "AND ( :#{#query.compagnieId} is null OR d.compagnie.id=:#{#query.compagnieId} ) " +
        "AND ( :#{#query.etatDemandeId} is null OR d.etatDemandeHist.etatDemande.id=:#{#query.etatDemandeId} ) " +
        "AND ( :#{#query.resultatDemandeId} is null OR d.resultatDemande.id=:#{#query.resultatDemandeId})  " +
        "AND ( :#{#query.mediateur} is not null OR d.etatDemandeHist.etatDemande.id= 4 OR d.dateTraitement is not null)"+
        "ORDER BY d.dateEtat ASC")
    Page<DemandeLight> filterDDetDF(@Param("query") DemandeDto demandeDto, Pageable pageable);

}
