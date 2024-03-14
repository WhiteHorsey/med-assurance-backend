package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Alerte;
import ma.medass.repository.AlerteRepository;
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
public class AlerteResource {

    private static final String ENTITY_NAME = "alerte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AlerteRepository alerteRepository;

    public AlerteResource(AlerteRepository alerteRepository) {
        this.alerteRepository = alerteRepository;
    }

    @PostMapping("/prv/alerte")
    public ResponseEntity<Alerte> createAlerte(@Valid @RequestBody Alerte alerte) throws URISyntaxException {
        Alerte result = alerteRepository.save(alerte);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/alerte")
    public ResponseEntity<Alerte> updateAlerte(@Valid @RequestBody Alerte alerte) throws URISyntaxException {
        Alerte result = alerteRepository.save(alerte);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/alerte")
    public List<Alerte> listAlerte() {
        return alerteRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/alerte/{id}")
    public ResponseEntity<Alerte> getAlerte(@PathVariable Long id) {
        Optional<Alerte> alerte = alerteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(alerte);
    }

    @DeleteMapping("/prv/alerte/{id}")
    public ResponseEntity<Void> deleteAlerte(@PathVariable Long id) {
        alerteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/alerte/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = alerteRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}