package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Civilite;
import ma.medass.repository.CiviliteRepository;
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
public class CiviliteResource {

    private static final String ENTITY_NAME = "civilite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CiviliteRepository civiliteRepository;

    public CiviliteResource(CiviliteRepository civiliteRepository) {
        this.civiliteRepository = civiliteRepository;
    }

    @PostMapping("/prv/civilite")
    public ResponseEntity<Civilite> createCivilite(@Valid @RequestBody Civilite civilite) throws URISyntaxException {
        Civilite result = civiliteRepository.save(civilite);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/civilite")
    public ResponseEntity<Civilite> updateCivilite(@Valid @RequestBody Civilite civilite) throws URISyntaxException {
        Civilite result = civiliteRepository.save(civilite);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/civilite")
    public List<Civilite> listCivilite() {
        return civiliteRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/civilite/{id}")
    public ResponseEntity<Civilite> getCivilite(@PathVariable Long id) {
        Optional<Civilite> civilite = civiliteRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(civilite);
    }

    @DeleteMapping("/prv/civilite/{id}")
    public ResponseEntity<Void> deleteCivilite(@PathVariable Long id) {
        civiliteRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/civilite/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = civiliteRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}