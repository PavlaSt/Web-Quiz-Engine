package engine;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface QuizRepository extends JpaRepository<Question, Long> {

}
