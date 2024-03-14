package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Demandeur;
import ma.medass.repository.DemandeurRepository;
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
public class DemandeurResource {

    private static final String ENTITY_NAME = "demandeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeurRepository demandeurRepository;

    public DemandeurResource(DemandeurRepository demandeurRepository) {
        this.demandeurRepository = demandeurRepository;
    }

    @PostMapping("/prv/demandeur")
    public ResponseEntity<Demandeur> createDemandeur(@Valid @RequestBody Demandeur demandeur) throws URISyntaxException {
        Demandeur result = demandeurRepository.save(demandeur);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/demandeur")
    public ResponseEntity<Demandeur> updateDemandeur(@Valid @RequestBody Demandeur demandeur) throws URISyntaxException {
        Demandeur result = demandeurRepository.save(demandeur);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/demandeur")
    public List<Demandeur> listDemandeur() {
        return demandeurRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/demandeur/{id}")
    public ResponseEntity<Demandeur> getDemandeur(@PathVariable Long id) {
        Optional<Demandeur> demandeur = demandeurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(demandeur);
    }

    @DeleteMapping("/prv/demandeur/{id}")
    public ResponseEntity<Void> deleteDemandeur(@PathVariable Long id) {
        demandeurRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/demandeur/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = demandeurRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}