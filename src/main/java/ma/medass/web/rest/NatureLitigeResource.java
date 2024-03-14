package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.NatureLitige;
import ma.medass.repository.NatureLitigeRepository;
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
public class NatureLitigeResource {

    private static final String ENTITY_NAME = "natureLitige";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NatureLitigeRepository natureLitigeRepository;

    public NatureLitigeResource(NatureLitigeRepository natureLitigeRepository) {
        this.natureLitigeRepository = natureLitigeRepository;
    }

    @PostMapping("/prv/natureLitige")
    public ResponseEntity<NatureLitige> createNatureLitige(@Valid @RequestBody NatureLitige natureLitige) throws URISyntaxException {
        NatureLitige result = natureLitigeRepository.save(natureLitige);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/natureLitige")
    public ResponseEntity<NatureLitige> updateNatureLitige(@Valid @RequestBody NatureLitige natureLitige) throws URISyntaxException {
        NatureLitige result = natureLitigeRepository.save(natureLitige);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/natureLitige")
    public List<NatureLitige> listNatureLitige() {
        return natureLitigeRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/natureLitige/{id}")
    public ResponseEntity<NatureLitige> getNatureLitige(@PathVariable Long id) {
        Optional<NatureLitige> natureLitige = natureLitigeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(natureLitige);
    }

    @DeleteMapping("/prv/natureLitige/{id}")
    public ResponseEntity<Void> deleteNatureLitige(@PathVariable Long id) {
        natureLitigeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/natureLitige/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = natureLitigeRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}