package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.Branche;
import ma.medass.repository.BrancheRepository;
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
public class BrancheResource {

    private static final String ENTITY_NAME = "branche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BrancheRepository brancheRepository;

    public BrancheResource(BrancheRepository brancheRepository) {
        this.brancheRepository = brancheRepository;
    }

    @PostMapping("/prv/branche")
    public ResponseEntity<Branche> createBranche(@Valid @RequestBody Branche branche) throws URISyntaxException {
        Branche result = brancheRepository.save(branche);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/branche")
    public ResponseEntity<Branche> updateBranche(@Valid @RequestBody Branche branche) throws URISyntaxException {
        Branche result = brancheRepository.save(branche);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/branche")
    public List<Branche> listBranche() {
        return brancheRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/branche/{id}")
    public ResponseEntity<Branche> getBranche(@PathVariable Long id) {
        Optional<Branche> branche = brancheRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(branche);
    }

    @DeleteMapping("/prv/branche/{id}")
    public ResponseEntity<Void> deleteBranche(@PathVariable Long id) {
        brancheRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/branche/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = brancheRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}