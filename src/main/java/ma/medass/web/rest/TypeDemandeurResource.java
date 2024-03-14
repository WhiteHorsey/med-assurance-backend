package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Tuple;
import javax.validation.Valid;
import ma.medass.domain.TypeDemandeur;
import ma.medass.repository.TypeDemandeurRepository;
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
public class TypeDemandeurResource {

    private static final String ENTITY_NAME = "typeDemandeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeDemandeurRepository typeDemandeurRepository;

    public TypeDemandeurResource(TypeDemandeurRepository typeDemandeurRepository) {
        this.typeDemandeurRepository = typeDemandeurRepository;
    }

    @PostMapping("/prv/typeDemandeur")
    public ResponseEntity<TypeDemandeur> createTypeDemandeur(@Valid @RequestBody TypeDemandeur typeDemandeur) throws URISyntaxException {
        TypeDemandeur result = typeDemandeurRepository.save(typeDemandeur);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/typeDemandeur")
    public ResponseEntity<TypeDemandeur> updateTypeDemandeur(@Valid @RequestBody TypeDemandeur typeDemandeur) throws URISyntaxException {
        TypeDemandeur result = typeDemandeurRepository.save(typeDemandeur);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/typeDemandeur")
    public List<TypeDemandeur> listTypeDemandeur() {
        return typeDemandeurRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/typeDemandeur/{id}")
    public ResponseEntity<TypeDemandeur> getTypeDemandeur(@PathVariable Long id) {
        Optional<TypeDemandeur> typeDemandeur = typeDemandeurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(typeDemandeur);
    }

    @DeleteMapping("/prv/typeDemandeur/{id}")
    public ResponseEntity<Void> deleteTypeDemandeur(@PathVariable Long id) {
        typeDemandeurRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/typeDemandeur/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = typeDemandeurRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
            .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
            .collect(Collectors.toList());
            return dtoList;
    }
}