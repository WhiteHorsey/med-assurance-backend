package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.EtatDemande;
import ma.medass.repository.EtatDemandeRepository;
import ma.medass.service.dto.ReferenceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;


@RestController
@RequestMapping("/api")
@Transactional
public class EtatDemandeResource {

    private static final String ENTITY_NAME = "etatDemande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatDemandeRepository etatDemandeRepository;

    public EtatDemandeResource(EtatDemandeRepository etatDemandeRepository) {
        this.etatDemandeRepository = etatDemandeRepository;
    }

    @PostMapping("/prv/etatDemande")
    public ResponseEntity<EtatDemande> createEtatDemande(@Valid @RequestBody EtatDemande etatDemande) throws URISyntaxException {
        EtatDemande result = etatDemandeRepository.save(etatDemande);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/etatDemande")
    public ResponseEntity<EtatDemande> updateEtatDemande(@Valid @RequestBody EtatDemande etatDemande) throws URISyntaxException {
        EtatDemande result = etatDemandeRepository.save(etatDemande);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/etatDemande")
    public List<EtatDemande> listEtatDemande() {
        return etatDemandeRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/etatDemande/{id}")
    public ResponseEntity<EtatDemande> getEtatDemande(@PathVariable Long id) {
        Optional<EtatDemande> etatDemande = etatDemandeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(etatDemande);
    }

    @DeleteMapping("/prv/etatDemande/{id}")
    public ResponseEntity<Void> deleteEtatDemande(@PathVariable Long id) {
        etatDemandeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/etatDemande/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = etatDemandeRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}