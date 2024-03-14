package ma.medass.repository;

import java.util.List;
import ma.medass.domain.EtatDemande;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatDemandeRepository extends JpaRepository<EtatDemande, Long> {

    List<EtatDemande> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from EtatDemande order by libelle")
    List<Tuple> listForReference();
}