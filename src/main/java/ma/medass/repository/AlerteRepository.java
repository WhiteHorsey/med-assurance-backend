package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Alerte;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Long> {

    List<Alerte> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Alerte order by libelle")
    List<Tuple> listForReference();
}