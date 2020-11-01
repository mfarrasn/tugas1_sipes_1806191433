package apap.tugas1.sipes.controller;


import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import apap.tugas1.sipes.model.PesawatTeknisiModel;
import apap.tugas1.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PesawatController {
    @Qualifier("pesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private TeknisiService teknisiService;

    @Autowired
    private PesawatTeknisi pesawatTeknisiService;

    @Autowired
    private TipeService tipeService;

    @GetMapping("/")
    private String home() {
        return "home";
    }

    @GetMapping("/pesawat")
    public String getPesawatList(Model model){
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        model.addAttribute("listPesawat", listPesawat);
        return "view-pesawat";
    }
    @GetMapping("/pesawat/tambah")
    public String addPesawatFormPage(Model model){
        PesawatModel pesawat = new PesawatModel();

        List<PesawatTeknisiModel> temp = new ArrayList<PesawatTeknisiModel>();
        PesawatTeknisiModel dummy = new PesawatTeknisiModel();
        dummy.setPesawat(pesawat);
        temp.add(dummy);
        pesawat.setList_pesawat_teknisi(temp);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTipe", tipeService.getListTipe());
        model.addAttribute("listTeknisi",teknisiService.getListTeknisi());
        return "form-add-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String addPesawatSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){
        pesawatService.addNomorSeri(pesawat);
//        List<PesawatTeknisiModel> pesawatTeknisiList = pesawat.getList_pesawat_teknisi();
//        for (PesawatTeknisiModel pesawat_teknisi: pesawatTeknisiList) {
//            pesawat_teknisi.setPesawat(pesawat);
//        }
        pesawatService.addPesawat(pesawat);
        model.addAttribute("maskapai", pesawat.getMaskapai());
        model.addAttribute("nomor_seri",pesawat.getNomor_seri());
        return "add-pesawat";
    }
    @GetMapping("/pesawat/{idPesawat}")
    public String viewDetailPesawat(
            @PathVariable Long idPesawat,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatbyId(idPesawat);
        model.addAttribute("pesawat",pesawat);
        model.addAttribute("tanggal_pesawat",pesawat.getDateTimeFormat());
        return "view-detail-pesawat";
    }
    @GetMapping("/pesawat/ubah/{idPesawat}")
    public String changePesawatForm(
            @PathVariable Long idPesawat,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatbyId(idPesawat);
        model.addAttribute("pesawat",pesawat);
        model.addAttribute("tanggal_pesawat",pesawat.getDateTimeFormat());
        model.addAttribute("tanggal_dibuat", pesawat.getCalendar().getTime());
        return "form-change-pesawat";
    }
    @PostMapping("/pesawat/ubah")
    public String changePesawatSubmit(
            @ModelAttribute PesawatModel pesawatModel,
            Model model
    ){
        PesawatModel pesawatModelupdate = pesawatService.changePesawat(pesawatModel);
        pesawatService.addNomorSeri(pesawatModelupdate);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("tipe", pesawatModel.getTipe().getNamatipe());
        model.addAttribute("nomor_seri",pesawatModelupdate.getNomor_seri());
        return "update-pesawat-view";

    }
    @GetMapping("/pesawat/{idPesawat}/tambah-penerbangan")
    public String assignPesawatForm(
            @PathVariable Long idPesawat,
            Model model
    ){
        PesawatModel pesawat = pesawatService.getPesawatbyId(idPesawat);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listPenerbangan",pesawat.getListPenerbangan());
        model.addAttribute("tanggal_pesawat", pesawat.getDateTimeFormat());
        model.addAttribute("all_penerbangan" , penerbanganService.getListPenerbangan());
        return "assign-penerbangan-form";
    }

    @PostMapping("/pesawat/{idPesawat}/tambah-penerbangan")
    public String assignPesawatSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model
    ){
        String nomorPenerbangan = pesawat.getListPenerbangan().get(0).getNomor_penerbangan();
        PesawatModel pesawat_updated = pesawatService.tambahPenerbangan(pesawat.getListPenerbangan().get(0) , pesawat);
        model.addAttribute("pesawat", pesawat);
        model.addAttribute("pesawat_updated", pesawat_updated);
        model.addAttribute("listPenerbangan", pesawat_updated.getListPenerbangan());
        model.addAttribute("tanggal_pesawat", pesawat_updated.getDateTimeFormat());
        model.addAttribute("all_penerbangan" , penerbanganService.getListPenerbangan());
        return "assign-penerbangan-form";

    }
    @GetMapping("/pesawat/pesawat-tua")
    public String viewPesawatTua(Model model){
        List<PesawatModel> pesawatModelList = pesawatService.getPesawatList();
        List<PesawatModel> listPesawatTua = new ArrayList<>();
        List<Integer> umurPesawatTua = new ArrayList<>();
        for (PesawatModel pesawat : pesawatModelList){
            if (pesawatService.getUmurPesawat(pesawat) > 10 ){
                listPesawatTua.add(pesawat);
                umurPesawatTua.add(pesawatService.getUmurPesawat(pesawat));
            }
        }
        model.addAttribute("listPesawatTua", listPesawatTua);
        model.addAttribute("listUmur", umurPesawatTua);
        return "view-pesawat-tua";
    }

}
