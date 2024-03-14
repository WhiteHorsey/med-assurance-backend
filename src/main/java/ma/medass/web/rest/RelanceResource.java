package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Relance;
import ma.medass.repository.RelanceRepository;
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
public class RelanceResource {

    private static final String ENTITY_NAME = "relance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelanceRepository relanceRepository;

    public RelanceResource(RelanceRepository relanceRepository) {
        this.relanceRepository = relanceRepository;
    }

    @PostMapping("/prv/relance")
    public ResponseEntity<Relance> createRelance(@Valid @RequestBody Relance relance) throws URISyntaxException {
        Relance result = relanceRepository.save(relance);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/relance")
    public ResponseEntity<Relance> updateRelance(@Valid @RequestBody Relance relance) throws URISyntaxException {
        Relance result = relanceRepository.save(relance);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/relance")
    public List<Relance> listRelance() {
        return relanceRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/relance/{id}")
    public ResponseEntity<Relance> getRelance(@PathVariable Long id) {
        Optional<Relance> relance = relanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(relance);
    }

    @DeleteMapping("/prv/relance/{id}")
    public ResponseEntity<Void> deleteRelance(@PathVariable Long id) {
        relanceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/relance/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = relanceRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}