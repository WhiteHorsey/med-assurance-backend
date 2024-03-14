package ma.medass.repository;

import java.util.List;
import ma.medass.domain.Actualite;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualiteRepository extends JpaRepository<Actualite, Long> {

    List<Actualite> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Actualite order by libelle")
    List<Tuple> listForReference();
}