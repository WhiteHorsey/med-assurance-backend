package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Demandeur;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeurRepository extends JpaRepository<Demandeur, Long> {

    List<Demandeur> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Demandeur order by libelle")
    List<Tuple> listForReference();
}