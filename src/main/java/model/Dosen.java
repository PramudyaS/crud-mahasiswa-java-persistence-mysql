package model;

import jakarta.persistence.*;
import org.eclipse.persistence.annotations.PrimaryKey;

import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@Entity
@XmlRootElement
@Table(name = "dosen")
@NamedQueries({
        @NamedQuery(name = "Dosen.findAll", query = "SELECT d FROM Dosen d")
        , @NamedQuery(name = "Dosen.findByKoded", query = "SELECT d FROM Dosen d WHERE d.koded = :koded")
        , @NamedQuery(name = "Dosen.findByNamad", query = "SELECT d FROM Dosen d WHERE d.namad = :namad")})
public class Dosen implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "kode")
    private String koded;

    @Basic(optional = false)
    @Column(name = "nama")
    private String namad;

    public Dosen() {
    }

    public Dosen(String koded) {
        this.koded = koded;
    }

    public Dosen(String koded, String namad) {
        this.koded = koded;
        this.namad = namad;
    }

    public String getKoded() {
        return koded;
    }

    public void setKoded(String koded) {
        String oldKoded = this.koded;
        this.koded = koded;
        changeSupport.firePropertyChange("koded", oldKoded, koded);
    }

    public String getNamad() {
        return namad;
    }

    public void setNamad(String namad) {
        String oldNamad = this.namad;
        this.namad = namad;
        changeSupport.firePropertyChange("namad", oldNamad, namad);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (koded != null ? koded.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosen)) {
            return false;
        }
        Dosen other = (Dosen) object;
        if ((this.koded == null && other.koded != null) || (this.koded != null && !this.koded.equals(other.koded))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "model.Dosen[ koded=" + koded + " ]";
        return koded + "-" + namad;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
