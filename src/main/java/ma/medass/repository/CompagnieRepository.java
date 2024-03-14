package ma.medass.repository;

import java.util.List;
import java.util.Optional;
import ma.medass.domain.Compagnie;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompagnieRepository extends JpaRepository<Compagnie, Long> {

    List<Compagnie> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from Compagnie order by libelle")
    List<Tuple> listForReference();

    
    @Query("select c from Compagnie c left outer join User u on u.compagnie.id=c.id where u.id=:userId")
    Optional<Compagnie> getByUserId(@Param("userId")  Long userId);

}
