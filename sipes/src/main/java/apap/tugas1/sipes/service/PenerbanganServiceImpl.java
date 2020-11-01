package apap.tugas1.sipes.service;


import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import apap.tugas1.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{

    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public List<PenerbanganModel> getListPenerbangan() {
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganbyId(Long id) {
        return penerbanganDb.findById(id).get();
    }

    @Override
    public void addPenerbangan(PenerbanganModel penerbanganModel) {
        penerbanganDb.save(penerbanganModel);
    }

    @Override
    public PenerbanganModel changePenerbangan(PenerbanganModel penerbanganModel) {
        PenerbanganModel penerbangan_tujuan = penerbanganDb.findById(penerbanganModel.getId()).get();

        penerbangan_tujuan.setNomor_penerbangan(penerbanganModel.getNomor_penerbangan());
        penerbangan_tujuan.setKode_bandara_asal(penerbanganModel.getKode_bandara_asal());
        penerbangan_tujuan.setKode_bandara_tujuan(penerbanganModel.getKode_bandara_tujuan());
        penerbangan_tujuan.setWaktu_berangkat(penerbanganModel.getWaktu_berangkat());
        penerbanganDb.save(penerbangan_tujuan);
        return  penerbangan_tujuan;
    }

    @Override
    public void deletePenerbangan(PenerbanganModel penerbanganModel) {
        penerbanganDb.delete(penerbanganModel);
    }
}
