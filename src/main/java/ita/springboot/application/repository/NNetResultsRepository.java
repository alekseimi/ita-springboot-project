package ita.springboot.application.repository;


import ita.springboot.application.model.NNetResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NNetResultsRepository extends JpaRepository<NNetResult, Long> {
    Page<NNetResult> findByUserId(Long userId, Pageable pageable);

    Optional<NNetResult> findByIdAndUserId(Long id, Long postId);
}
