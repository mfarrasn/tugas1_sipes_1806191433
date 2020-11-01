package apap.tugas1.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;


@Entity
@Table(name="penerbangan")
public class PenerbanganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode_bandara_asal", nullable = false)
    private String kode_bandara_asal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode_bandara_tujuan", nullable = false)
    private String kode_bandara_tujuan;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "waktu_berangkat", nullable = false)
    private Date waktu_berangkat;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_penerbangan",nullable = false, unique = true )
    private String nomor_penerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "id_pesawat", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode_bandara_asal() {
        return kode_bandara_asal;
    }

    public void setKode_bandara_asal(String kode_bandara_asal) {
        this.kode_bandara_asal = kode_bandara_asal;
    }

    public String getKode_bandara_tujuan() {
        return kode_bandara_tujuan;
    }

    public void setKode_bandara_tujuan(String kode_bandara_tujuan) {
        this.kode_bandara_tujuan = kode_bandara_tujuan;
    }

    public Date getWaktu_berangkat() {
        return waktu_berangkat;
    }

    public void setWaktu_berangkat(Date waktu_berangkat) {
        this.waktu_berangkat = waktu_berangkat;
    }

    public String getNomor_penerbangan() {
        return nomor_penerbangan;
    }

    public void setNomor_penerbangan(String nomor_penerbangan) {
        this.nomor_penerbangan = nomor_penerbangan;
    }

    public PesawatModel getPesawat() {
        return pesawat;
    }

    public void setPesawat(PesawatModel pesawat) {
        this.pesawat = pesawat;
    }

    public String getDateTimeFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String datestr = formatter.format(waktu_berangkat);
        return datestr;
    }
}
