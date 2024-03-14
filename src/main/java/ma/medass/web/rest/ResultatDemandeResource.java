package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.ResultatDemande;
import ma.medass.repository.ResultatDemandeRepository;
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
public class ResultatDemandeResource {

    private static final String ENTITY_NAME = "resultatDemande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResultatDemandeRepository resultatDemandeRepository;

    public ResultatDemandeResource(ResultatDemandeRepository resultatDemandeRepository) {
        this.resultatDemandeRepository = resultatDemandeRepository;
    }

    @PostMapping("/prv/resultatDemande")
    public ResponseEntity<ResultatDemande> createResultatDemande(@Valid @RequestBody ResultatDemande resultatDemande) throws URISyntaxException {
        ResultatDemande result = resultatDemandeRepository.save(resultatDemande);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/resultatDemande")
    public ResponseEntity<ResultatDemande> updateResultatDemande(@Valid @RequestBody ResultatDemande resultatDemande) throws URISyntaxException {
        ResultatDemande result = resultatDemandeRepository.save(resultatDemande);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/resultatDemande")
    public List<ResultatDemande> listResultatDemande() {
        return resultatDemandeRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/resultatDemande/{id}")
    public ResponseEntity<ResultatDemande> getResultatDemande(@PathVariable Long id) {
        Optional<ResultatDemande> resultatDemande = resultatDemandeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(resultatDemande);
    }

    @DeleteMapping("/prv/resultatDemande/{id}")
    public ResponseEntity<Void> deleteResultatDemande(@PathVariable Long id) {
        resultatDemandeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/resultatDemande/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = resultatDemandeRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}