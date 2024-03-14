package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Compagnie;
import ma.medass.repository.CompagnieRepository;
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
public class CompagnieResource {

    private static final String ENTITY_NAME = "compagnie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompagnieRepository compagnieRepository;

    public CompagnieResource(CompagnieRepository compagnieRepository) {
        this.compagnieRepository = compagnieRepository;
    }

    @PostMapping("/prv/compagnie")
    public ResponseEntity<Compagnie> createCompagnie(@Valid @RequestBody Compagnie compagnie) throws URISyntaxException {
        Compagnie result = compagnieRepository.save(compagnie);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/compagnie")
    public ResponseEntity<Compagnie> updateCompagnie(@Valid @RequestBody Compagnie compagnie) throws URISyntaxException {
        Compagnie result = compagnieRepository.save(compagnie);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/compagnie")
    public List<Compagnie> listCompagnie() {
        return compagnieRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/compagnie/{id}")
    public ResponseEntity<Compagnie> getCompagnie(@PathVariable Long id) {
        Optional<Compagnie> compagnie = compagnieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(compagnie);
    }

    @DeleteMapping("/prv/compagnie/{id}")
    public ResponseEntity<Void> deleteCompagnie(@PathVariable Long id) {
        compagnieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/compagnie/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = compagnieRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}