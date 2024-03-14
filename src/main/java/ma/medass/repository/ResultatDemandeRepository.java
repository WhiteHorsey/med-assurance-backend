package ma.medass.repository;

import java.util.List;
import ma.medass.domain.ResultatDemande;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatDemandeRepository extends JpaRepository<ResultatDemande, Long> {

    List<ResultatDemande> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from ResultatDemande order by libelle")
    List<Tuple> listForReference();
}