package judgeSystem.repositories;

import judgeSystem.domain.entities.Contest;
import judgeSystem.domain.entities.MaxResultForContest;
import judgeSystem.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaxResultForContestRepository extends JpaRepository<MaxResultForContest, Long>{

    MaxResultForContest getOneByContestAndUser(Contest contest, User user);
}
