package apap.tugas1.sipes.repository;

import apap.tugas1.sipes.model.TeknisiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TeknisiDb extends JpaRepository<TeknisiModel, Long>{
    Optional<TeknisiModel> findById (Long id);
}
