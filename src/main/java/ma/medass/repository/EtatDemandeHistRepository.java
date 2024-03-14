package ma.medass.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.medass.domain.EtatDemandeHist;

@Repository
public interface EtatDemandeHistRepository extends JpaRepository<EtatDemandeHist, Long> {

    List<EtatDemandeHist> findAllByOrderByIdDesc();

    @Query(value = "select id, libelle from EtatDemandeHist order by libelle")
    List<Tuple> listForReference();

    void deleteByDemandeId(Long demandeId);

    List<EtatDemandeHist> findAllByDemandeIdOrderByIdDesc(Long demandeId);

    Optional<EtatDemandeHist> findFirstByDemandeIdOrderByIdDesc(Long demandeId);

    List<EtatDemandeHist> findByDemandeIdAndEtatDemandeId(Long demandeId, Long etatDemandeId);
}