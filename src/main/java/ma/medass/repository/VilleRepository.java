package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Ville;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {

    List<Ville> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Ville order by libelle")
    List<Tuple> listForReference();
}