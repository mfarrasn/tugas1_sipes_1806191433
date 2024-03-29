package apap.tugas1.sipes.model;

import org.hibernate.validator.constraints.UniqueElements;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import java.util.List;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name="maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomor_seri", nullable = false , unique = true)
    private String nomor_seri;

    @NotNull
    @Size(max=255)
    @Column(name = "tempat_dibuat", nullable = false)
    private String tempat_dibuat;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "tanggal_dibuat", nullable = false)
    private Date tanggal_dibuat;

    @NotNull
    @Size(max=255)
    @Column(name = "jenis_pesawat", nullable = false)
    private String jenis_pesawat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_tipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> list_pesawat_teknisi;

    public List<PesawatTeknisiModel> getList_pesawat_teknisi() {
        return list_pesawat_teknisi;
    }

    public void setList_pesawat_teknisi(List<PesawatTeknisiModel> list_pesawat_teknisi) {
        this.list_pesawat_teknisi = list_pesawat_teknisi;
    }

    public Long getId() {
        return id;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomor_seri() {
        return nomor_seri;
    }

    public void setNomor_seri(String nomor_seri) {
        this.nomor_seri = nomor_seri;
    }

    public String getTempat_dibuat() {
        return tempat_dibuat;
    }

    public void setTempat_dibuat(String tempat_dibuat) {
        this.tempat_dibuat = tempat_dibuat;
    }

    public Date getTanggal_dibuat() {
        return tanggal_dibuat;
    }

    public void setTanggal_dibuat(Date tanggal_dibuat) {
        this.tanggal_dibuat = tanggal_dibuat;
    }

    public String getJenis_pesawat() {
        return jenis_pesawat;
    }

    public void setJenis_pesawat(String jenis_pesawat) {
        this.jenis_pesawat = jenis_pesawat;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public Calendar getCalendar(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggal_dibuat);
        return cal;
    }
    public String getDateTimeFormat(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String datestr = formatter.format(tanggal_dibuat);
        return datestr;
    }
}
