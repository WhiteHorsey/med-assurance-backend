package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.ObjetLitige;
import ma.medass.repository.ObjetLitigeRepository;
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
public class ObjetLitigeResource {

    private static final String ENTITY_NAME = "objetLitige";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjetLitigeRepository objetLitigeRepository;

    public ObjetLitigeResource(ObjetLitigeRepository objetLitigeRepository) {
        this.objetLitigeRepository = objetLitigeRepository;
    }

    @PostMapping("/prv/objetLitige")
    public ResponseEntity<ObjetLitige> createObjetLitige(@Valid @RequestBody ObjetLitige objetLitige) throws URISyntaxException {
        ObjetLitige result = objetLitigeRepository.save(objetLitige);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/objetLitige")
    public ResponseEntity<ObjetLitige> updateObjetLitige(@Valid @RequestBody ObjetLitige objetLitige) throws URISyntaxException {
        ObjetLitige result = objetLitigeRepository.save(objetLitige);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/objetLitige")
    public List<ObjetLitige> listObjetLitige() {
        return objetLitigeRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/objetLitige/{id}")
    public ResponseEntity<ObjetLitige> getObjetLitige(@PathVariable Long id) {
        Optional<ObjetLitige> objetLitige = objetLitigeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(objetLitige);
    }

    @DeleteMapping("/prv/objetLitige/{id}")
    public ResponseEntity<Void> deleteObjetLitige(@PathVariable Long id) {
        objetLitigeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/objetLitige/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = objetLitigeRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}