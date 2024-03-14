package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Branche;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface BrancheRepository extends JpaRepository<Branche, Long> {

    List<Branche> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Branche order by libelle")
    List<Tuple> listForReference();
}