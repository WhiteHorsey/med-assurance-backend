package ma.medass.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A Actualite.
 */
@Entity
@Table(name = "actualite")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Actualite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "commentaire")
    private String commentaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Actualite id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public Actualite libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateModification() {
        return this.dateModification;
    }

    public Actualite dateModification(LocalDate dateModification) {
        this.setDateModification(dateModification);
        return this;
    }

    public void setDateModification(LocalDate dateModification) {
        this.dateModification = dateModification;
    }

    public String getCommentaire() {
        return this.commentaire;
    }

    public Actualite commentaire(String commentaire) {
        this.setCommentaire(commentaire);
        return this;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Actualite)) {
            return false;
        }
        return id != null && id.equals(((Actualite) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Actualite{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", dateModification='" + getDateModification() + "'" +
            ", commentaire='" + getCommentaire() + "'" +
            "}";
    }

    @Transient
    private String _type = "actualite";

    public String get_type() {
        return this._type;
    }
}
