package judgeSystem.repositories;

import judgeSystem.domain.entities.Contest;
import judgeSystem.domain.entities.MaxResultForProblem;
import judgeSystem.domain.entities.Problem;
import judgeSystem.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaxResultForProblemRepository extends JpaRepository<MaxResultForProblem, Long>{

    MaxResultForProblem getOneByProblemAndUser(Problem problem, User user);

    @Query("SELECT m from MaxResultForProblem m where m.user=:user and m.problem.contest = :contest")
    List<MaxResultForProblem> getMaxResultSubmissionsList(@Param(value = "user") User user, @Param(value = "contest") Contest contest);
}
