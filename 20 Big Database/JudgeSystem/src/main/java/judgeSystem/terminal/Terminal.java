package judgeSystem.terminal;

import judgeSystem.config.Config;
import judgeSystem.controllers.*;
import judgeSystem.io.impl.ConsoleIOImpl;
import judgeSystem.io.impl.FileIOImpl;
import judgeSystem.parser.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(noRollbackFor = MyException.class)
public class Terminal implements CommandLineRunner {

    private final ConsoleIOImpl consoleIO;
    private final FileIOImpl fileIO;
    private final CategoryController categoryController;
    private final StrategyController strategyController;
    private final ContestController contestController;
    private final UserController userController;
    private final ProblemController problemController;
    private final SubmissionController submissionController;

    @Autowired
    public Terminal(ConsoleIOImpl consoleIO,
                    FileIOImpl fileIO,
                    CategoryController categoryController,
                    StrategyController strategyController,
                    ContestController contestController,
                    UserController userController,
                    ProblemController problemController,
                    SubmissionController submissionController) {
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
        this.categoryController = categoryController;
        this.strategyController = strategyController;
        this.contestController = contestController;
        this.userController = userController;
        this.problemController = problemController;
        this.submissionController = submissionController;
    }

    @Override
    public void run(String... strings) {
        this.consoleIO.write(this.categoryController.insertCategory(this.fileIO.read(Config.CATEGORIES_JSON)));
        this.consoleIO.write(this.strategyController.insertStrategy(this.fileIO.read(Config.STRAREGIES_JSON)));
        this.consoleIO.write(this.contestController.insertContest(this.fileIO.read(Config.CONTESTS_JSON)));
        this.consoleIO.write(this.userController.insertUser(this.fileIO.read(Config.USERS_JSON)));
        this.consoleIO.write(this.contestController.enrollUser(this.fileIO.read(Config.USER_PARTICIPATIONS_XML)));
        this.consoleIO.write(this.problemController.importProblem(this.fileIO.read(Config.PROBLEMS_XML)));
        this.consoleIO.write(this.submissionController.importSubmission(this.fileIO.read(Config.SUBMISSIONS_XML)));
    }

}
