package apap.tugas1.sipes.repository;

import apap.tugas1.sipes.model.PenerbanganModel;
import apap.tugas1.sipes.model.PesawatModel;
import apap.tugas1.sipes.model.PesawatTeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PesawatTeknisiDb extends JpaRepository<PesawatTeknisiModel, Long>{
    Optional<PesawatTeknisiModel> findById (Long id);
}
