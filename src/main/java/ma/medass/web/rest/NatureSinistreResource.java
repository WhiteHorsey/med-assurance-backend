package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.NatureSinistre;
import ma.medass.repository.NatureSinistreRepository;
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
public class NatureSinistreResource {

    private static final String ENTITY_NAME = "natureSinistre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureSinistreRepository natureSinistreRepository;

    public NatureSinistreResource(NatureSinistreRepository natureSinistreRepository) {
        this.natureSinistreRepository = natureSinistreRepository;
    }

    @PostMapping("/prv/natureSinistre")
    public ResponseEntity<NatureSinistre> createNatureSinistre(@Valid @RequestBody NatureSinistre natureSinistre) throws URISyntaxException {
        NatureSinistre result = natureSinistreRepository.save(natureSinistre);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/natureSinistre")
    public ResponseEntity<NatureSinistre> updateNatureSinistre(@Valid @RequestBody NatureSinistre natureSinistre) throws URISyntaxException {
        NatureSinistre result = natureSinistreRepository.save(natureSinistre);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/natureSinistre")
    public List<NatureSinistre> listNatureSinistre() {
        return natureSinistreRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/natureSinistre/{id}")
    public ResponseEntity<NatureSinistre> getNatureSinistre(@PathVariable Long id) {
        Optional<NatureSinistre> natureSinistre = natureSinistreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(natureSinistre);
    }

    @DeleteMapping("/prv/natureSinistre/{id}")
    public ResponseEntity<Void> deleteNatureSinistre(@PathVariable Long id) {
        natureSinistreRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/natureSinistre/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = natureSinistreRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}