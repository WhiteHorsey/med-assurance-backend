package ma.medass.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.medass.domain.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    List<Demande> findByDemandeurLibelleIgnoreCaseAndLibelleIgnoreCase(String demandeurLibelle, String numeroDemande);

    Page<Demande> findAllByOrderByDateDemandeDesc(Pageable pageable);

    List<Demande> findAllByOrderByDateDemandeDesc();

    @Query(value = "select id, libelle from Demande order by libelle")
    List<Tuple> listForReference();

    Optional<Demande> findByLibelle(String libelle);

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'mm-YYYY') FROM Demande d WHERE date_part('year',d.dateDemande) > :anneeEncours and d.etatDemandeHist.etatDemande.id != 6 GROUP BY to_char(d.dateDemande, 'mm-YYYY') ORDER BY to_char(d.dateDemande, 'mm-YYYY') ")
    public List<Tuple> listeNombreParMoisAnneeEnCours(@Param("anneeEncours") int anneeEncours);

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'mm-YYYY') FROM Demande d WHERE date_part('year',d.dateDemande) > :anneeEncours and d.etatDemandeHist.etatDemande.id = 6 GROUP BY to_char(d.dateDemande, 'mm-YYYY') ORDER BY to_char(d.dateDemande, 'mm-YYYY') ")
    public List<Tuple> listeNombreClotureeParMoisAnneeEnCours(@Param("anneeEncours") int anneeEncours);

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'YYYY') FROM Demande d WHERE d.etatDemandeHist.etatDemande.id != 6 GROUP BY to_char(d.dateDemande, 'YYYY') ORDER BY to_char(d.dateDemande, 'YYYY') ")
    public List<Tuple> listeNombreEnCoursParAnnee();

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'YYYY') FROM Demande d WHERE d.etatDemandeHist.etatDemande.id = 6 GROUP BY to_char(d.dateDemande, 'YYYY') ORDER BY to_char(d.dateDemande, 'YYYY') ")
    public List<Tuple> listeNombreClotureeParAnnee();

    @Query(value = "SELECT count(d), d.compagnie.libelle FROM Demande d GROUP BY d.compagnie.libelle ORDER BY d.compagnie.libelle ")
    public List<Tuple> listeNombreParCompagnie();

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'YYYY'), d.compagnie.libelle FROM Demande d GROUP BY d.compagnie.libelle, to_char(d.dateDemande, 'YYYY') ORDER BY d.compagnie.libelle ")
    public List<Tuple> listeNombreParCompagnieParAnnee();

    @Query(value = "SELECT count(d), d.etatDemandeHist.etatDemande.libelle FROM Demande d GROUP BY d.etatDemandeHist.etatDemande.libelle ORDER BY d.etatDemandeHist.etatDemande.libelle ")
    public List<Tuple> listeNombreParEtat();

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'YYYY'), d.etatDemandeHist.etatDemande.libelle FROM Demande d GROUP BY d.etatDemandeHist.etatDemande.libelle, to_char(d.dateDemande, 'YYYY') ORDER BY d.etatDemandeHist.etatDemande.libelle")
    public List<Tuple> listeNombreParEtatParAnnee();

    @Query(value = "SELECT count(d), d.etatDemandeHist.etatDemande.libelle FROM Demande d WHERE date_part('year',d.dateDemande) > :anneeEncours GROUP BY d.etatDemandeHist.etatDemande.libelle ORDER BY d.etatDemandeHist.etatDemande.libelle ")
    public List<Tuple> listeNombreParEtatAnneeEnCours(@Param("anneeEncours") int anneeEncours);

    @Query(value = "SELECT count(d), d.resultatDemande.libelle FROM Demande d GROUP BY d.resultatDemande.libelle ORDER BY d.resultatDemande.libelle ")
    public List<Tuple> listeNombreParResultatCloture();

    @Query(value = "SELECT count(d), to_char(d.dateDemande, 'YYYY'), d.resultatDemande.libelle FROM Demande d GROUP BY d.resultatDemande.libelle, to_char(d.dateDemande, 'YYYY') ORDER BY d.resultatDemande.libelle ")
    public List<Tuple> listeNombreParResultatClotureParAnnee();

    @Query(value = "SELECT count(d), d.resultatDemande.libelle FROM Demande d WHERE date_part('year',d.dateDemande) > :anneeEncours GROUP BY d.resultatDemande.libelle ORDER BY d.resultatDemande.libelle ")
    public List<Tuple> listeNombreParResultatClotureAnneeEnCours(@Param("anneeEncours") int anneeEncours);

    @Query("select count(d) from Demande d where d.dateDemande>= :#{#dateDebut} AND d.dateDemande <= :#{#dateFin}")
    Integer nombreAnneeEnCours(@Param("dateDebut") LocalDate dateDebut, @Param("dateFin") LocalDate dateFin);

}
