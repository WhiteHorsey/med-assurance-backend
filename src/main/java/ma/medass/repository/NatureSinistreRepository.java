package ma.medass.repository;

import java.util.List;
import ma.medass.domain.NatureSinistre;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureSinistreRepository extends JpaRepository<NatureSinistre, Long> {

    List<NatureSinistre> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from NatureSinistre order by libelle")
    List<Tuple> listForReference();
}