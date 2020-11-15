package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;


/*Controller for saving in memory*/
//@RestController
public class Controller {

    private QuestionRepository repository;

    public Controller(QuestionRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/api/quiz")
    public Question getQuestion() {
        return Question.QUESTION;
    }

    @PostMapping(path = "/api/quiz")
    public Answer checkAnswer(@RequestParam(value = "answer") int[] option) {
        System.out.println("checkAnswer " + option );
        if (Question.QUESTION.isCorrect(option)) {
            return Answer.CORRECT_ANSWER;
        } else {
            return Answer.WRONG_ANSWER;
        }
    }
    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Answer checkSolution(@PathVariable Long id,
                                @Valid  @RequestBody (required = false) Guess guess){
        System.out.println("checkSolution :" + id + "-"+ guess);
        Question question = repository.findById(id);
        if (question.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        System.out.println("controller answer/option: " + guess);

        if(question.isCorrect(guess.getAnswer())) {
            return Answer.CORRECT_ANSWER;
        } else {
            return Answer.WRONG_ANSWER;
        }
    }

    @PostMapping(path = "/api/quizzes")
    @ResponseBody
    public Question createQuestion(@Valid @RequestBody Question postedQuestion) {
        System.out.println("createQuestion: " + postedQuestion );
        repository.save(postedQuestion);
        return repository.findLast();
    }

    @GetMapping(path = "/api/quizzes")
    public List<Question> showAllQuestions() {
        System.out.println("showAllQuestions");
        return repository.findAll();
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Question showQuestionById(@PathVariable Long id) {
        System.out.println("showQuestionById: " + id);
        return repository.findById(id);
    }



}
