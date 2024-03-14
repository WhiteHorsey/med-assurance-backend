package ma.medass.repository;

import java.util.List;
import ma.medass.domain.NatureLitige;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureLitigeRepository extends JpaRepository<NatureLitige, Long> {

    List<NatureLitige> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from NatureLitige order by libelle")
    List<Tuple> listForReference();
}