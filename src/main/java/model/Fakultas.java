package model;

import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@Entity
@Table(name = "fakultas")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Fakultas.findAll", query = "SELECT f FROM Fakultas f")
        , @NamedQuery(name = "Fakultas.findByKode", query = "SELECT f FROM Fakultas f WHERE f.kode = :kode")
        , @NamedQuery(name = "Fakultas.findByNama", query = "SELECT f FROM Fakultas f WHERE f.nama = :nama")})
public class Fakultas implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Basic(optional = false)
    @Column(name = "kode")
    private String kode;
    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    public Fakultas() {
    }

    public Fakultas(String kode) {
        this.kode = kode;
    }

    public Fakultas(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        String oldKode = this.kode;
        this.kode = kode;
        changeSupport.firePropertyChange("kode", oldKode, kode);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kode != null ? kode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fakultas)) {
            return false;
        }
        Fakultas other = (Fakultas) object;
        if ((this.kode == null && other.kode != null) || (this.kode != null && !this.kode.equals(other.kode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "model.Fakultas[ kode=" + kode + " ]";
        return kode + "-" + nama;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }


}
