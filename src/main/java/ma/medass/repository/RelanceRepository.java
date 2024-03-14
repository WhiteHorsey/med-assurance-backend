package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Relance;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RelanceRepository extends JpaRepository<Relance, Long> {

    List<Relance> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Relance order by libelle")
    List<Tuple> listForReference();
}