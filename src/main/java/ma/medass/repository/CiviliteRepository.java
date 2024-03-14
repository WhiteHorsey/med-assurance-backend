package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Civilite;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CiviliteRepository extends JpaRepository<Civilite, Long> {

    List<Civilite> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Civilite order by libelle")
    List<Tuple> listForReference();
}