package apap.tugas1.sipes.controller;

import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import apap.tugas1.sipes.service.PenerbanganService;
import apap.tugas1.sipes.service.PesawatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PenerbanganController {
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private PesawatService pesawatService;

    @GetMapping("/penerbangan")
    public String getPenerbanganList(Model model){
        List<PenerbanganModel> penerbanganModelList = penerbanganService.getListPenerbangan();
        model.addAttribute("listPenerbangan", penerbanganModelList);
//        model.addAttribute("waktu_berangkat", penerbanganModelList.iterator().next().getDateTimeFormat());
        return "view-penerbangan";
    }
    @GetMapping("/penerbangan/{idPenerbangan}")
    public String viewDetailPenerbangan(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganbyId(idPenerbangan);
        PesawatModel pesawat = penerbangan.getPesawat();
        Boolean hasPesawat = pesawat != null;
        model.addAttribute("penerbangan",penerbangan);
        model.addAttribute("hasPesawat",hasPesawat);
        model.addAttribute("waktu_berangkat", penerbangan.getDateTimeFormat());
        return "view-detail-penerbangan";
    }
    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model){
        model.addAttribute("penerbangan",new PenerbanganModel());
        return "form-add-penerbangan";
    }
    @PostMapping("/penerbangan/tambah")
    public String addPenerbanganSubmit(
        @ModelAttribute PenerbanganModel penerbangan,
        Model model
    ){
        penerbanganService.addPenerbangan(penerbangan);
        model.addAttribute("kode_penerbangan",penerbangan.getNomor_penerbangan());
        return "add-penerbangan";
    }
    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    public String changePenerbanganForm(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbangan = penerbanganService.getPenerbanganbyId(idPenerbangan);
        model.addAttribute("penerbangan",penerbangan);
        return "form-change-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String changePenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbanganModel,
            Model model
    ){
        PenerbanganModel penerbanganupdated = penerbanganService.changePenerbangan(penerbanganModel);
        model.addAttribute("penerbangan", penerbanganModel);
        model.addAttribute("nomor_penerbangan", penerbanganModel.getNomor_penerbangan());
        return "update-penerbangan-view";

    }

    @GetMapping("/penerbangan/hapus/{idPenerbangan}")
    public String deletePenerbangan(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbanganModel = penerbanganService.getPenerbanganbyId(idPenerbangan);
        model.addAttribute("nomor_penerbangan", penerbanganModel.getNomor_penerbangan());
        penerbanganService.deletePenerbangan(penerbanganModel);
        return "delete-penerbangan-view";

    }
}
