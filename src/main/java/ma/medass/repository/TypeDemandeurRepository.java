package ma.medass.repository;

import java.util.List;
import ma.medass.domain.TypeDemandeur;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDemandeurRepository extends JpaRepository<TypeDemandeur, Long> {

    List<TypeDemandeur> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from TypeDemandeur order by libelle")
    List<Tuple> listForReference();
}