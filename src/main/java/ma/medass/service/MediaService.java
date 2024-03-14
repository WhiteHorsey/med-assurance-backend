package ma.medass.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import ma.medass.domain.Media;
import ma.medass.repository.MediaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Service for sending emails.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
public class MediaService {

    private static final String IMG_DIR = "C:/medass";

    private final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public Media save(Media media) {
        String fileType = media.getFileContentType() != null && media.getFileContentType().indexOf("/") > -1 ? media.getFileContentType().substring(media.getFileContentType().indexOf("/") + 1) : "";
        fileType = fileType.equals("svg+xml")?"svg":fileType;
        fileType = fileType.equals("plain")?"txt":fileType;
        String newLien = media.getOwnerType() + "/" + media.getKey() + "." + media.getOwnerId() + "." + fileType;
        File f = new File(IMG_DIR + media.getLien());
        if (f.exists()) {
            f.delete();
        }
        media.setLien(newLien);
        try (OutputStream stream = new FileOutputStream(IMG_DIR + media.getLien())) {
            stream.write(media.getFile());
            Media result = mediaRepository.save(media);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}
