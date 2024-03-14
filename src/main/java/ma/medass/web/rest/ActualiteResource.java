package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Actualite;
import ma.medass.repository.ActualiteRepository;
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
public class ActualiteResource {

    private static final String ENTITY_NAME = "actualite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActualiteRepository actualiteRepository;

    public ActualiteResource(ActualiteRepository actualiteRepository) {
        this.actualiteRepository = actualiteRepository;
    }

    @PostMapping("/prv/actualite")
    public ResponseEntity<Actualite> createActualite(@Valid @RequestBody Actualite actualite) throws URISyntaxException {
        Actualite result = actualiteRepository.save(actualite);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/actualite")
    public ResponseEntity<Actualite> updateActualite(@Valid @RequestBody Actualite actualite) throws URISyntaxException {
        Actualite result = actualiteRepository.save(actualite);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/actualite")
    public List<Actualite> listActualite() {
        return actualiteRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/actualite/{id}")
    public ResponseEntity<Actualite> getActualite(@PathVariable Long id) {
        Optional<Actualite> actualite = actualiteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(actualite);
    }

    @DeleteMapping("/prv/actualite/{id}")
    public ResponseEntity<Void> deleteActualite(@PathVariable Long id) {
        actualiteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/actualite/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = actualiteRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}