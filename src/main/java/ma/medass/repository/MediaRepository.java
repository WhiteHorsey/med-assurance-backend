package ma.medass.repository;

import java.util.List;
import java.util.Optional;
import ma.medass.domain.Media;
import javax.persistence.Tuple;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    List<Media> findAllByOrderByOrderAsc();

    @Query(value = "select id, libelle from Media order by libelle")
    List<Tuple> listForReference();

    @Query("select m from Media m where m.ownerType=:ownerType and m.ownerId=:ownerId and m.libelle not in ('avatar','affiche','logo')")
    List<Media> findAllByOwnerTypeAndOwnerIdOrderByOrdreAsc(@Param("ownerType")  String ownerType, @Param("ownerId")   Long ownerId);

    @Query("select m from Media m where m.ownerType=:ownerType and m.ownerId=:ownerId and m.libelle=:libelle")
    Optional<Media> findByOwnerTypeAndOwnerIdAndLibelle(@Param("ownerType")  String ownerType, @Param("ownerId")   Long ownerId, @Param("libelle")   String libelle);

    @Modifying
    @Query("update Media m set m.libelle =:libelle, m.lien =:lien, m.key =:key where id=:id")
    void updateMediaWithoutFile(@Param("id") Long id, @Param("libelle")  String libelle, @Param("lien")  String lien, @Param("key")  String key);
}
