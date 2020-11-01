package apap.tugas1.sipes.service;

import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import apap.tugas1.sipes.repository.PenerbanganDb;
import apap.tugas1.sipes.service.PenerbanganService;
import apap.tugas1.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{

    @Autowired
    PesawatDb pesawatDb;

    @Autowired
    PenerbanganDb penerbanganDb;

    @Autowired
    PenerbanganService penerbanganService;

    @Override
    public List<PesawatModel> getPesawatList() {
        return pesawatDb.findAll();
    }

    @Override
    public void addPesawat(PesawatModel pesawatModel) {
        pesawatDb.save(pesawatModel);
    }

    @Override
    public void addNomorSeri(PesawatModel pesawatModel) {
        StringBuilder nomor_seri = new StringBuilder(13);
        switch (pesawatModel.getJenis_pesawat()){
            case "Komersial":
                nomor_seri.append(1);
                break;
            case "Militer" :
                nomor_seri.append(2);
                break;
        }
        switch (pesawatModel.getTipe().getNamatipe()){
            case "Boeing":
                nomor_seri.append("BO");
                break;
            case "ATR" :
                nomor_seri.append("AT");
                break;
            case "Airbus" :
                nomor_seri.append("AB");
                break;
            case "Bombardier" :
                nomor_seri.append("BB");
                break;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(pesawatModel.getTanggal_dibuat());
        int tahun = cal.get(Calendar.YEAR);
//        tahun = tahun + 1900;
        String tahun_str = Integer.toString(tahun);
        StringBuilder tahunreverse = new StringBuilder(tahun_str);
        tahunreverse.reverse();
        nomor_seri.append(tahunreverse);
        nomor_seri.append(tahun + 8);

        Random rd = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char huruf_pertama = abc.charAt(rd.nextInt(abc.length()));
        char huruf_kedua = abc.charAt(rd.nextInt(abc.length()));
        nomor_seri.append(huruf_pertama);
        nomor_seri.append(huruf_kedua);

        String nomor_seri_str = nomor_seri.toString();
        pesawatModel.setNomor_seri(nomor_seri_str);
        pesawatDb.save(pesawatModel);

    }

    @Override
    public PesawatModel getPesawatbyId(Long id) {
        return pesawatDb.findById(id).get();
    }

    @Override
    public PesawatModel changePesawat(PesawatModel pesawatModel) {
        PesawatModel pesawat_tujuan = pesawatDb.findById(pesawatModel.getId()).get();

        pesawat_tujuan.setMaskapai(pesawatModel.getMaskapai());
        pesawat_tujuan.setTanggal_dibuat(pesawatModel.getTanggal_dibuat());
        pesawat_tujuan.setTempat_dibuat(pesawatModel.getTempat_dibuat());
        pesawat_tujuan.setJenis_pesawat(pesawatModel.getJenis_pesawat());

        pesawatDb.save(pesawat_tujuan);
        return  pesawat_tujuan;

    }

    @Override
    public PesawatModel tambahPenerbangan(PenerbanganModel penerbanganModel, PesawatModel pesawatModel) {
        PesawatModel pesawat_tujuan = pesawatDb.findById(penerbanganModel.getId()).get();
        PenerbanganModel targetpenerbangan = penerbanganDb.findById(penerbanganModel.getId()).get();

        pesawat_tujuan.getListPenerbangan().add(targetpenerbangan);
        targetpenerbangan.setPesawat(pesawat_tujuan);
        penerbanganDb.save(targetpenerbangan);
        pesawatDb.save(pesawatModel);
        return pesawat_tujuan;

    }

    @Override
    public Integer getUmurPesawat(PesawatModel pesawatModel) {
        Calendar cal = Calendar.getInstance();
        Integer tahun_dibuat = pesawatModel.getCalendar().get(Calendar.YEAR);
        Integer tahun_ini = cal.get(Calendar.YEAR);
        return (tahun_ini - tahun_dibuat);
    }

}
