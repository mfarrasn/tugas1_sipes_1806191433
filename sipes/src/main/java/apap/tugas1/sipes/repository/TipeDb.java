package apap.tugas1.sipes.repository;

import apap.tugas1.sipes.model.TipeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface TipeDb extends JpaRepository<TipeModel,Long> {
    Optional<TipeModel> findById(Long id);
}
