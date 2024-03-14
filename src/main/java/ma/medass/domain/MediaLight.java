package ma.medass.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Media.
 */
@Entity
@Table(name = "media")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MediaLight implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "jhi_order")
    private Integer order;

    @Column(name = "key")
    private String key;

    @Column(name = "lien")
    private String lien;

    @Column(name = "type_lien")
    private String typeLien;

    @Column(name = "owner_type")
    private String ownerType;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "file_content_type")
    private String fileContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    @Transient
    private String _type = "media";

    public Long getId() {
        return this.id;
    }

    public MediaLight id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public MediaLight libelle(String libelle) {
        this.setLibelle(libelle);
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getOrder() {
        return this.order;
    }

    public MediaLight order(Integer order) {
        this.setOrder(order);
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getKey() {
        return this.key;
    }

    public MediaLight key(String key) {
        this.setKey(key);
        return this;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLien() {
        return this.lien;
    }

    public MediaLight lien(String lien) {
        this.setLien(lien);
        return this;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getTypeLien() {
        return this.typeLien;
    }

    public MediaLight typeLien(String typeLien) {
        this.setTypeLien(typeLien);
        return this;
    }

    public void setTypeLien(String typeLien) {
        this.typeLien = typeLien;
    }

    public String getOwnerType() {
        return this.ownerType;
    }

    public MediaLight ownerType(String ownerType) {
        this.setOwnerType(ownerType);
        return this;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Long getOwnerId() {
        return this.ownerId;
    }

    public MediaLight ownerId(Long ownerId) {
        this.setOwnerId(ownerId);
        return this;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public MediaLight fileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MediaLight)) {
            return false;
        }
        return id != null && id.equals(((MediaLight) o).id);
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MediaLight{" +
                "id=" + getId() +
                ", libelle='" + getLibelle() + "'" +
                ", order=" + getOrder() +
                ", key='" + getKey() + "'" +
                ", lien='" + getLien() + "'" +
                ", typeLien='" + getTypeLien() + "'" +
                ", ownerType='" + getOwnerType() + "'" +
                ", ownerId=" + getOwnerId() +
                ", fileContentType='" + getFileContentType() + "'" +
                "}";
    }

    public String get_type() {
        return this._type;
    }
}
