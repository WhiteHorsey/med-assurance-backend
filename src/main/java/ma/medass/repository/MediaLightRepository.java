package ma.medass.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.medass.domain.MediaLight;

@Repository
public interface MediaLightRepository extends JpaRepository<MediaLight, Long> {

    List<MediaLight> findAllByOrderByOrderAsc();

    @Query(value = "select id, libelle from MediaLight order by libelle")
    List<Tuple> listForReference();

    @Query("select m from MediaLight m where m.ownerType=:ownerType and m.ownerId=:ownerId and m.libelle not in ('avatar','affiche','logo')")
    List<MediaLight> findAllByOwnerTypeAndOwnerIdOrderByOrdreAsc(@Param("ownerType") String ownerType,
            @Param("ownerId") Long ownerId);

    @Query("select m from MediaLight m where m.ownerType=:ownerType and m.ownerId=:ownerId and m.libelle=:libelle")
    Optional<MediaLight> findByOwnerTypeAndOwnerIdAndLibelle(@Param("ownerType") String ownerType,
            @Param("ownerId") Long ownerId, @Param("libelle") String libelle);

    @Modifying
    @Query("update MediaLight m set m.libelle =:libelle, m.lien =:lien, m.key =:key where id=:id")
    void updateMediaWithoutFile(@Param("id") Long id, @Param("libelle") String libelle, @Param("lien") String lien,
            @Param("key") String key);
}
