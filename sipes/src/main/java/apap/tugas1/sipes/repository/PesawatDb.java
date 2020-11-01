package apap.tugas1.sipes.repository;

import apap.tugas1.sipes.model.PesawatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PesawatDb extends JpaRepository<PesawatModel,Long> {
    Optional<PesawatModel> findById(Long id);

}
