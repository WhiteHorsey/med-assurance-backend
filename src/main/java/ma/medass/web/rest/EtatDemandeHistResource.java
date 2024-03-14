package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.medass.domain.Demande;
import ma.medass.domain.EtatDemande;
import ma.medass.domain.EtatDemandeHist;
import ma.medass.domain.MediaLight;
import ma.medass.domain.User;
import ma.medass.repository.DemandeRepository;
import ma.medass.repository.EtatDemandeHistRepository;
import ma.medass.repository.EtatDemandeRepository;
import ma.medass.repository.MediaLightRepository;
import ma.medass.repository.ResultatDemandeRepository;
import ma.medass.service.MailService;
import ma.medass.service.TypeMail;
import ma.medass.service.UserService;
import ma.medass.service.dto.EtatDemandeHistDto;
import ma.medass.service.dto.ReferenceDto;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class EtatDemandeHistResource {

    private static final long NOUVELLE = 1;
    private static final long EN_ETUDE = 2;
    private static final long COMPLEMENT = 3;
    private static final long ATTENTE_CIE = 4;
    private static final long TRAITEMENT_CIE = 5;
    private static final long CLOTUREE = 6;
    private static final long COMMENTAIRE = 7;

    private static final String ENTITY_NAME = "etatDemandeHist";
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtatDemandeHistRepository etatDemandeHistRepository;
    private final DemandeRepository demandeRepository;
    private final EtatDemandeRepository etatDemandeRepository;
    private final ResultatDemandeRepository resultatDemandeRepository;
    private final MediaLightRepository mediaLightRepository;
    private final MailService mailService;
    private final UserService userService;

    public EtatDemandeHistResource(EtatDemandeHistRepository etatDemandeHistRepository,
            DemandeRepository demandeRepository, EtatDemandeRepository etatDemandeRepository,
            ResultatDemandeRepository resultatDemandeRepository, MediaLightRepository mediaLightRepository,
            MailService mailService, UserService userService) {
        this.etatDemandeHistRepository = etatDemandeHistRepository;
        this.demandeRepository = demandeRepository;
        this.etatDemandeRepository = etatDemandeRepository;
        this.resultatDemandeRepository = resultatDemandeRepository;
        this.mediaLightRepository = mediaLightRepository;
        this.mailService = mailService;
        this.userService = userService;
    }

    @PostMapping("/pbl/etatDemandeHist")
    public ResponseEntity<EtatDemandeHist> createEtatDemandeHistParDemandeur(
            @Valid @RequestBody EtatDemandeHistDto etatDemandeHistDto) throws URISyntaxException {
        return createEtatDemandeHist(etatDemandeHistDto);
    }

    @PostMapping("/prv/etatDemandeHist")
    public ResponseEntity<EtatDemandeHist> createEtatDemandeHist(
            @Valid @RequestBody EtatDemandeHistDto etatDemandeHistDto) throws URISyntaxException {

        Demande demande = demandeRepository.findById(etatDemandeHistDto.getDemande().getId()).get();
        EtatDemande etatDemande = etatDemandeRepository.findById(etatDemandeHistDto.getEtatDemande().getId()).get();
        LocalDate dateCreation = LocalDate.now();
        EtatDemandeHist etatDemandeHist = new EtatDemandeHist();
        etatDemandeHist.setLibelle(etatDemande.getLibelle());
        etatDemandeHist.setDateEtat(dateCreation);
        etatDemandeHist.commentaire(etatDemandeHistDto.getCommentaire());
        etatDemandeHist.setEtatDemande(etatDemande);
        etatDemandeHist.setDemande(demande);
        EtatDemandeHist resultEtatDemandeHist = etatDemandeHistRepository.save(etatDemandeHist);
        demande.setEtatDemandeHist(resultEtatDemandeHist);
        if (etatDemandeHistDto.getResultatDemande() != null && etatDemandeHistDto.getResultatDemande().getId() != null) {
            demande.setResultatDemande(resultatDemandeRepository.findById(etatDemandeHistDto.getResultatDemande().getId()).get());
        }

        if (etatDemande.getId() == EN_ETUDE && demande.getDateEtude() == null) {
            demande.setDateEtude(dateCreation);
        } else if (etatDemande.getId() == TRAITEMENT_CIE && demande.getDateTraitement() == null) {
            demande.setDateTraitement(dateCreation);
        }

        demandeRepository.save(demande);

        return ResponseEntity.ok().body(resultEtatDemandeHist);
    }

    @PutMapping("/prv/etatDemandeHist")
    public ResponseEntity<EtatDemandeHist> updateEtatDemandeHist(@Valid @RequestBody EtatDemandeHist etatDemandeHist)
            throws URISyntaxException {
        EtatDemandeHist result = etatDemandeHistRepository.save(etatDemandeHist);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/etatDemandeHist")
    public List<EtatDemandeHist> listEtatDemandeHist() {
        return etatDemandeHistRepository.findAllByOrderByIdDesc();
    }

    @GetMapping("/pbl/etatDemandeHist/{id}")
    public ResponseEntity<EtatDemandeHist> getEtatDemandeHist(@PathVariable Long id) {
        Optional<EtatDemandeHist> etatDemandeHist = etatDemandeHistRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(etatDemandeHist);
    }

    @GetMapping("/pbl/etatDemandeHist/last/{demandeId}")
    public ResponseEntity<EtatDemandeHist> getLastEtatDemandeHist(@PathVariable Long demandeId) {
        Optional<EtatDemandeHist> etatDemandeHist = etatDemandeHistRepository
                .findFirstByDemandeIdOrderByIdDesc(demandeId);
        return ResponseUtil.wrapOrNotFound(etatDemandeHist);
    }

    @DeleteMapping("/prv/etatDemandeHist/{id}")
    public ResponseEntity<Void> deleteEtatDemandeHist(@PathVariable Long id) {
        etatDemandeHistRepository.deleteById(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    @GetMapping("/pbl/etatDemandeHist/demande/{demandeId}")
    public List<EtatDemandeHist> listEtatDemandeHistByDemandeId(@PathVariable Long demandeId) {
        List<EtatDemandeHist> listeEtatDemandeHist = etatDemandeHistRepository
                .findAllByDemandeIdOrderByIdDesc(demandeId);
        for (EtatDemandeHist etatDemandeHist : listeEtatDemandeHist) {
            List<MediaLight> listeMedia = mediaLightRepository.findAllByOwnerTypeAndOwnerIdOrderByOrdreAsc(
                    "etatDemandeHist",
                    etatDemandeHist.getId());
            etatDemandeHist.setListeMedia(listeMedia);
        }
        return listeEtatDemandeHist;
    }

    @DeleteMapping("/prv/etatDemandeHist/demande/{demandeId}")
    public ResponseEntity<Void> deleteByDemandeId(@PathVariable Long demandeId) {
        etatDemandeHistRepository.deleteByDemandeId(demandeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pbl/etatDemandeHist/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = etatDemandeHistRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
                .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
                .collect(Collectors.toList());
        return dtoList;
    }
}
