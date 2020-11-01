package apap.tugas1.sipes.service;

import java.util.List;
import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import ch.qos.logback.core.joran.action.AppenderRefAction;

public interface PenerbanganService {
    List<PenerbanganModel> getListPenerbangan();

    PenerbanganModel getPenerbanganbyId(Long id);

    void addPenerbangan(PenerbanganModel penerbanganModel);

    PenerbanganModel changePenerbangan(PenerbanganModel penerbanganModel);

    void deletePenerbangan(PenerbanganModel penerbanganModel);
}
