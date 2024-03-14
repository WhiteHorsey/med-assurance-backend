package ma.medass.web.rest;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import ma.medass.domain.Compagnie;
import ma.medass.domain.Demande;
import ma.medass.domain.DemandeLight;
import ma.medass.domain.EtatDemande;
import ma.medass.domain.EtatDemandeHist;
import ma.medass.domain.User;
import ma.medass.repository.CompagnieRepository;
import ma.medass.repository.DemandeLightRepository;
import ma.medass.repository.DemandeRepository;
import ma.medass.repository.DemandeurRepository;
import ma.medass.repository.EtatDemandeHistRepository;
import ma.medass.repository.EtatDemandeRepository;
import ma.medass.service.MailService;
import ma.medass.service.TypeMail;
import ma.medass.service.UserService;
import ma.medass.service.dto.DemandeDto;
import ma.medass.service.dto.DemandeParChampParAnneeDto;
import ma.medass.service.dto.PlotDto;
import ma.medass.service.dto.ReferenceDto;
import ma.medass.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class DemandeResource {

    private static final String ENTITY_NAME = "demande";
    private static final Long MEDIATEUR_ID = 1L;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DemandeRepository demandeRepository;
    private final DemandeLightRepository demandeLightRepository;
    private final EtatDemandeRepository etatDemandeRepository;
    private final DemandeurRepository demandeurRepository;
    private final CompagnieRepository compagnieRepository;
    private final EtatDemandeHistRepository etatDemandeHistRepository;
    private final MailService mailService;
    private final UserService userService;

    public DemandeResource(DemandeRepository demandeRepository, CompagnieRepository compagnieRepository, EtatDemandeRepository etatDemandeRepository, DemandeurRepository demandeurRepository, DemandeLightRepository demandeLightRepository, EtatDemandeHistRepository etatDemandeHistRepository, MailService mailService, UserService userService) {
        this.demandeRepository = demandeRepository;
        this.demandeLightRepository = demandeLightRepository;
        this.etatDemandeRepository = etatDemandeRepository;
        this.demandeurRepository = demandeurRepository;
        this.compagnieRepository = compagnieRepository;
        this.etatDemandeHistRepository = etatDemandeHistRepository;
        this.mailService = mailService;
        this.userService = userService;
    }

    @PostMapping("/pbl/demande")
    public ResponseEntity<Demande> createDemande(@Valid @RequestBody Demande demande) {
        System.out.println(demande.toString());
        LocalDate dateCreation = LocalDate.now();
        demande.setDateDemande(dateCreation);
        Optional<EtatDemande> etatDemande = etatDemandeRepository.findById(1L);
        demande.setDemandeur(demandeurRepository.save(demande.getDemandeur())); // DELETE DEMANDER
        Demande result = demandeRepository.save(demande);

        EtatDemandeHist etatDemandeHist = new EtatDemandeHist();
        etatDemandeHist.setEtatDemande(etatDemande.get());
        etatDemandeHist.setDateEtat(dateCreation);
        etatDemandeHist.setDemande(result);
        etatDemandeHist.setLibelle(etatDemande.get().getLibelle());
        EtatDemandeHist resultEtatDemandeHist = etatDemandeHistRepository.save(etatDemandeHist);

        String sid = "" + result.getId();
        result.setLibelle("DEM" + StringUtils.leftPad(sid, 7, "0"));
        result.setEtatDemandeHist(resultEtatDemandeHist);
        result = demandeRepository.save(demande);
        System.out.println("NOUVELLE DEMANDE ENREGISTRe : " + result);

        Optional<User> mediateur = userService.getUserByِCompagnieId(MEDIATEUR_ID);
        if (mediateur.isPresent()) {
            mailService.sendEmailAdmin(mediateur.get().getEmail(), TypeMail.CHANGEMENT_ETAT_ADMIN, resultEtatDemandeHist,result);
            mailService.sendEmail(result.getDemandeur().getEmail(), TypeMail.CHANGEMENT_ETAT, resultEtatDemandeHist, result, mediateur.get().getEmail());
        }

        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/demande")
    public ResponseEntity<Demande> updateDemande(@Valid @RequestBody Demande demande) throws URISyntaxException {
        Demande result = demandeRepository.save(demande);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/pbl/demande")
    public List<Demande> listDemande() {
        return demandeRepository.findAllByOrderByDateDemandeDesc();
    }

    @GetMapping("/pbl/demande/{id}")
    public ResponseEntity<Demande> getDemande(@PathVariable Long id) throws BadRequestAlertException {
        Optional<Demande> demande = demandeRepository.findById(id);
        Optional<User> user = userService.getUser();
        if (user.isPresent()) {
            if (MEDIATEUR_ID.equals(user.get().getCompagnie().getId())) {
                return ResponseUtil.wrapOrNotFound(demande);
            }
            Optional<Compagnie> compagnie = compagnieRepository.getByUserId(user.get().getId());
            if (compagnie.isPresent() && demande.isPresent() && compagnie.get().getId().equals(demande.get().getCompagnie().getId())) {
                return ResponseUtil.wrapOrNotFound(demande);
            }
        }
        throw new BadRequestAlertException("Demande non authorise", "demande", "demandeNonAuthorise");

    }

    @GetMapping("/pbl/demande/numero/{libelle}")
    public ResponseEntity<Demande> getDemande(@PathVariable String libelle) {
        Optional<Demande> demande = demandeRepository.findByLibelle(libelle);
        return ResponseUtil.wrapOrNotFound(demande);
    }

    @PostMapping("/pbl/demande/suivi")
    public List<Demande> getDemande(@RequestBody DemandeDto demandeDto) {
        return demandeRepository.findByDemandeurLibelleIgnoreCaseAndLibelleIgnoreCase(demandeDto.getNom(), demandeDto.getLibelle());
    }

    @DeleteMapping("/prv/demande/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        demandeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/pbl/demande/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = demandeRepository.listForReference();
        return list.stream().map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class))).collect(Collectors.toList());
    }

    @PostMapping("/pbl/demande/filter")
    public Page<DemandeLight> filterDemande(@RequestBody DemandeDto demandeDto, @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        boolean ddn = demandeDto.getDateDebutDemande() == null;
        boolean dfn = demandeDto.getDateFinDemande() == null;
        demandeDto.setMediateur(null);
        if (demandeDto.getCompagnieId() != null && demandeDto.getCompagnieId().equals(MEDIATEUR_ID)) {
            demandeDto.setCompagnieId(null);
            demandeDto.setMediateur("true");
        }
        if (ddn && dfn) {
            return demandeLightRepository.filter(demandeDto, pageable);
        } else if (ddn && !dfn) {
            return demandeLightRepository.filterDF(demandeDto, pageable);
        }
        if (!ddn && dfn) {
            return demandeLightRepository.filterDD(demandeDto, pageable);
        }
        return demandeLightRepository.filterDDetDF(demandeDto, pageable);
    }

    @GetMapping("/pbl/demande/nombreAnneeEnCours")
    public ResponseEntity<Integer> nombreAnneeEnCours() {
        LocalDate now = LocalDate.now();
        LocalDate dateDebut = now.with(firstDayOfYear());
        LocalDate dateFin = now.with(lastDayOfYear());
        Integer nombre = demandeRepository.nombreAnneeEnCours(dateDebut, dateFin);
        return ResponseEntity.ok().body(nombre);
    }

    @GetMapping("/pbl/demande/listeNombreParMoisAnneeEnCours")
    public List<PlotDto> listeNombreParMoisAnneeEnCours() {
        LocalDate toDay = LocalDate.now();
        List<Tuple> liste = demandeRepository.listeNombreParMoisAnneeEnCours(toDay.getYear() - 1);
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(t.get(1, String.class), t.get(0, Long.class), "En cours"));
        }
        List<Tuple> liste2 = demandeRepository.listeNombreClotureeParMoisAnneeEnCours(toDay.getYear() - 1);
        for (Tuple t : liste2) {
            plots.add(new PlotDto(t.get(1, String.class), t.get(0, Long.class), "Cloturée"));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParAnnee")
    public List<PlotDto> listeNombreParAnnee() {
        List<Tuple> liste = demandeRepository.listeNombreEnCoursParAnnee();
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(t.get(1, String.class), t.get(0, Long.class), "En cours"));
        }
        List<Tuple> liste2 = demandeRepository.listeNombreClotureeParAnnee();
        for (Tuple t : liste2) {
            plots.add(new PlotDto(t.get(1, String.class), t.get(0, Long.class), "Cloturée"));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParCompagnie")
    public List<PlotDto> listeNombreParCompagnie() {
        List<Tuple> liste = demandeRepository.listeNombreParCompagnie();
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(null, t.get(0, Long.class), t.get(1, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParCompagnieParAnnee")
    public List<DemandeParChampParAnneeDto> listeNombreParCompagnieParAnnee() {
        List<Tuple> liste = demandeRepository.listeNombreParCompagnieParAnnee();
        List<DemandeParChampParAnneeDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new DemandeParChampParAnneeDto(t.get(0, Long.class), t.get(1, String.class), t.get(2, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParEtat")
    public List<PlotDto> listeNombreParEtat() {
        List<Tuple> liste = demandeRepository.listeNombreParEtat();
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(null, t.get(0, Long.class), t.get(1, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParEtatParAnnee")
    public List<DemandeParChampParAnneeDto> listeNombreParEtatParAnnee() {
        List<Tuple> liste = demandeRepository.listeNombreParEtatParAnnee();
        List<DemandeParChampParAnneeDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new DemandeParChampParAnneeDto(t.get(0, Long.class), t.get(1, String.class), t.get(2, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParEtatAnneeEnCours")
    public List<PlotDto> listeNombreParEtatAnneeEnCours() {
        LocalDate toDay = LocalDate.now();
        List<Tuple> liste = demandeRepository.listeNombreParEtatAnneeEnCours(toDay.getYear() - 1);
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(null, t.get(0, Long.class), t.get(1, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParResultatCloture")
    public List<PlotDto> listeNombreParResultatCloture() {
        List<Tuple> liste = demandeRepository.listeNombreParResultatCloture();
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(null, t.get(0, Long.class), t.get(1, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParResultatClotureParAnnee")
    public List<DemandeParChampParAnneeDto> listeNombreParResultatClotureParAnnee() {
        List<Tuple> liste = demandeRepository.listeNombreParResultatClotureParAnnee();
        List<DemandeParChampParAnneeDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new DemandeParChampParAnneeDto(t.get(0, Long.class), t.get(1, String.class), t.get(2, String.class)));
        }
        return plots;
    }

    @GetMapping("/pbl/demande/listeNombreParResultatClotureAnneeEnCours")
    public List<PlotDto> listeNombreParResultatClotureAnneeEnCours() {
        LocalDate toDay = LocalDate.now();
        List<Tuple> liste = demandeRepository.listeNombreParResultatClotureAnneeEnCours(toDay.getYear() - 1);
        List<PlotDto> plots = new ArrayList<>();
        for (Tuple t : liste) {
            plots.add(new PlotDto(null, t.get(0, Long.class), t.get(1, String.class)));
        }
        return plots;
    }
}
