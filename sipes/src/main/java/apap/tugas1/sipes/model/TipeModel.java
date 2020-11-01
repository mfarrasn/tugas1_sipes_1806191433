package apap.tugas1.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipe")
public class TipeModel implements Serializable{

    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max=255)
    @Column(name = "deskripsi", nullable = false)
    private String deskripsi;

    @OneToMany(mappedBy = "tipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatModel> listPesawat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamatipe() {
        return nama;
    }

    public void setNamatipe(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public List<PesawatModel> getListPesawat() {
        return listPesawat;
    }

    public void setListPesawat(List<PesawatModel> listPesawat) {
        this.listPesawat = listPesawat;
    }

}
