package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Ville;
import ma.medass.repository.VilleRepository;
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
public class VilleResource {

    private static final String ENTITY_NAME = "ville";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VilleRepository villeRepository;

    public VilleResource(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @PostMapping("/prv/ville")
    public ResponseEntity<Ville> createVille(@Valid @RequestBody Ville ville) throws URISyntaxException {
        Ville result = villeRepository.save(ville);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/ville")
    public ResponseEntity<Ville> updateVille(@Valid @RequestBody Ville ville) throws URISyntaxException {
        Ville result = villeRepository.save(ville);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/ville")
    public List<Ville> listVille() {
        return villeRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/ville/{id}")
    public ResponseEntity<Ville> getVille(@PathVariable Long id) {
        Optional<Ville> ville = villeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ville);
    }

    @DeleteMapping("/prv/ville/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable Long id) {
        villeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/ville/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = villeRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}