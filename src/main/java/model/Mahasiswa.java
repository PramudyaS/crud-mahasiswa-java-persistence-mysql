package model;

import jakarta.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

@Entity
@Table(name = "mahasiswa")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Mahasiswa.findAll", query = "SELECT m FROM Mahasiswa m")
        , @NamedQuery(name = "Mahasiswa.findByNrp", query = "SELECT m FROM Mahasiswa m WHERE m.nrp = :nrp")
        , @NamedQuery(name = "Mahasiswa.findByNama", query = "SELECT m FROM Mahasiswa m WHERE m.nama = :nama")
        , @NamedQuery(name = "Mahasiswa.findByAngkatan", query = "SELECT m FROM Mahasiswa m WHERE m.angkatan = :angkatan")
        , @NamedQuery(name = "Mahasiswa.findByKodeFakultas", query = "SELECT m FROM Mahasiswa m WHERE m.kodeFakultas = :kodeFakultas")
        , @NamedQuery(name = "Mahasiswa.findByKodeDosen", query = "SELECT m FROM Mahasiswa m WHERE m.kodeDosen = :kodeDosen")})
public class Mahasiswa implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "nrp")
    private String nrp;

    @Basic(optional = false)
    @Column(name = "nama")
    private String nama;

    @Basic(optional = false)
    @Column(name = "angkatan")
    private String angkatan;

    @Basic(optional = false)
    @Column(name = "kode_fakultas")
    private String kodeFakultas;

    @Basic(optional = false)
    @Column(name = "kode_dosen")
    private String kodeDosen;

    public Mahasiswa() {
    }

    public Mahasiswa(String nrp) {
        this.nrp = nrp;
    }

    public Mahasiswa(String nrp, String nama, String angkatan, String kodeFakultas, String kodeDosen) {
        this.nrp = nrp;
        this.nama = nama;
        this.angkatan = angkatan;
        this.kodeFakultas = kodeFakultas;
        this.kodeDosen = kodeDosen;
    }

    public String getNrp() {
        return nrp;
    }

    public void setNrp(String nrp) {
        String oldNrp = this.nrp;
        this.nrp = nrp;
        changeSupport.firePropertyChange("nrp", oldNrp, nrp);
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        String oldNama = this.nama;
        this.nama = nama;
        changeSupport.firePropertyChange("nama", oldNama, nama);
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        String oldAngkatan = this.angkatan;
        this.angkatan = angkatan;
        changeSupport.firePropertyChange("angkatan", oldAngkatan, angkatan);
    }

    public String getKodeFakultas() {
        return kodeFakultas;
    }

    public void setKodeFakultas(String kodeFakultas) {
        String oldKodeFakultas = this.kodeFakultas;
        this.kodeFakultas = kodeFakultas;
        changeSupport.firePropertyChange("kodeFakultas", oldKodeFakultas, kodeFakultas);
    }

    public String getKodeDosen() {
        return kodeDosen;
    }

    public void setKodeDosen(String kodeDosen) {
        String oldKodeDosen = this.kodeDosen;
        this.kodeDosen = kodeDosen;
        changeSupport.firePropertyChange("kodeDosen", oldKodeDosen, kodeDosen);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrp != null ? nrp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mahasiswa)) {
            return false;
        }
        Mahasiswa other = (Mahasiswa) object;
        if ((this.nrp == null && other.nrp != null) || (this.nrp != null && !this.nrp.equals(other.nrp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Mahasiswa[ nrp=" + nrp + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
