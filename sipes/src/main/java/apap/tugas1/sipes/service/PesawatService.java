package apap.tugas1.sipes.service;

import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import java.util.List;

public interface PesawatService {
    List<PesawatModel> getPesawatList();

    void addPesawat(PesawatModel pesawatModel);

    void addNomorSeri(PesawatModel pesawatModel);

    PesawatModel getPesawatbyId(Long id);

    PesawatModel changePesawat(PesawatModel pesawatModel);

    PesawatModel tambahPenerbangan(PenerbanganModel penerbanganModel, PesawatModel pesawatModel);


    Integer getUmurPesawat(PesawatModel pesawatModel);
}
