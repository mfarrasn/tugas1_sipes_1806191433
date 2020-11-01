package apap.tugas1.sipes.service;

import apap.tugas1.sipes.model.PesawatModel;
import apap.tugas1.sipes.model.PesawatTeknisiModel;
import apap.tugas1.sipes.model.TeknisiModel;
import apap.tugas1.sipes.repository.PesawatTeknisiDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class PesawatTeknisiImpl implements PesawatTeknisi {

    @Autowired
    PesawatTeknisiDb pesawatTeknisiDb;

    @Override
    public void addPesawatTeknisi(PesawatTeknisiModel pesawatTeknisiModel) {
        pesawatTeknisiDb.save(pesawatTeknisiModel);
    }
}
