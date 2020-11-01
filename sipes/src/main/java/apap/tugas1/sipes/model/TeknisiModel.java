package apap.tugas1.sipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "teknisi")
public class TeknisiModel implements Serializable{

    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max=255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name="nomorTelepon", nullable = false)
    private Long nomorTelepon;

    @OneToMany(mappedBy = "teknisi", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PesawatTeknisiModel> list_pesawat_teknisi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(Long nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }
}
