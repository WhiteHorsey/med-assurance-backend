package ma.medass.repository;

import java.util.List;
import ma.medass.domain.ObjetLitige;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetLitigeRepository extends JpaRepository<ObjetLitige, Long> {

    List<ObjetLitige> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from ObjetLitige order by id")
    List<Tuple> listForReference();
}