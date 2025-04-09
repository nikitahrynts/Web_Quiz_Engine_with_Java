package engine.repository;

import engine.entity.Completion;
import engine.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface QuizCompletionRepository extends JpaRepository<Completion, Long>, PagingAndSortingRepository<Completion, Long> {

    @Query("select e from Completion e where e.user.email = :email")
    Page<Completion> findAllByUser(@Param("email") String email, Pageable pageable);
}
