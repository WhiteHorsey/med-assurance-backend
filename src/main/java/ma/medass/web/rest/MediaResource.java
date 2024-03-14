package ma.medass.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.medass.domain.Media;
import ma.medass.domain.MediaLight;
import ma.medass.repository.MediaLightRepository;
import ma.medass.repository.MediaRepository;
import ma.medass.service.dto.ReferenceDto;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
@Transactional
public class MediaResource {

    private static final String ENTITY_NAME = "media";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MediaRepository mediaRepository;
    private final MediaLightRepository mediaLightRepository;

    public MediaResource(MediaRepository mediaRepository, MediaLightRepository mediaLightRepository) {
        this.mediaRepository = mediaRepository;
        this.mediaLightRepository = mediaLightRepository;
    }

    // @PostMapping("/pbl/media")
    @RequestMapping(path = "/pbl/media", method = RequestMethod.POST, consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Media> createMedia(@RequestPart("file") MultipartFile file,
            @RequestPart("libelle") String libelle, @RequestPart("key") String key,
            @RequestPart("fileContentType") String fileContentType, @RequestPart("ownerType") String ownerType,
            @RequestPart("ownerId") String ownerId) throws Exception {
        Media m = new Media();
        m.setLibelle(libelle);
        m.setKey(key);
        m.setFile(file.getBytes());
        m.setFileContentType(fileContentType);
        m.setOwnerType(ownerType);
        m.setOwnerId(Long.parseLong(ownerId));
        Media result = mediaRepository.save(m);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/prv/media")
    public void updateMedia(@Valid @RequestBody Media media) throws URISyntaxException {
    }

    @GetMapping("/pbl/media")
    public List<Media> listMedia() {
        return mediaRepository.findAllByOrderByOrderAsc();
    }

    @GetMapping("/pbl/media/{id}")
    public ResponseEntity<Media> getMedia(@PathVariable Long id) {
        Optional<Media> media = mediaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(media);
    }

    @DeleteMapping("/prv/media/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        mediaRepository.deleteById(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    @GetMapping("/pbl/media/ownerType/{ownerType}/ownerId/{ownerId}")
    public List<MediaLight> listMediaByOwnerTypeAndOwnerId(@PathVariable String ownerType, @PathVariable Long ownerId) {
        return mediaLightRepository.findAllByOwnerTypeAndOwnerIdOrderByOrdreAsc(ownerType, ownerId);
    }

    @GetMapping("/pbl/media/owner/{ownerType}/owner/{ownerId}/libelle/{libelle}")
    public ResponseEntity<Media> getMediaByOwnerTypeAndOwnerIdAndName(@PathVariable String ownerType,
            @PathVariable Long ownerId, @PathVariable String libelle) {
        Optional<Media> media = mediaRepository.findByOwnerTypeAndOwnerIdAndLibelle(ownerType, ownerId, libelle);
        return ResponseUtil.wrapOrNotFound(media);
    }

    @GetMapping("/pbl/media/listForReference")
    public List<ReferenceDto> listForReference() {
        List<Tuple> list = mediaRepository.listForReference();
        List<ReferenceDto> dtoList = list.stream()
                .map(t -> new ReferenceDto(t.get(0, Long.class), t.get(1, String.class)))
                .collect(Collectors.toList());
        return dtoList;
    }

    @PutMapping("/prv/media/nofile")
    public ResponseEntity<Media> updateMediaWithoutFile(@Valid @RequestBody Media media) throws URISyntaxException {
        mediaRepository.updateMediaWithoutFile(media.getId(), media.getLibelle(), media.getLien(), media.getKey());
        return ResponseEntity.noContent().build();
    }
}
