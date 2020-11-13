package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class Controller {

    private QuestionRepository repository;

    public Controller(QuestionRepository repository) {
        this.repository = repository;
    }

    /*@GetMapping(path = "/api/quiz")
    public Question getQuestion() {
        return Question.QUESTION;
    }

    @PostMapping(path = "/api/quiz")
    public Answer checkAnswer(@RequestParam(value = "answer") int option) {
        if (Question.QUESTION.isCorrect(option)) {
            return Answer.CORRECT_ANSWER;
        } else {
            return Answer.WRONG_ANSWER;
        }
    }*/
    @PostMapping(path = "/api/quizzes/{id}/solve")
    public Answer checkSolution(@PathVariable Long id, @RequestParam(value = "answer") int option) {
        System.out.println("id: " + id);
        Question question = repository.findById(id);
        System.out.println(question);
        System.out.println("option: " + option);
        if (question.equals(null)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(question.isCorrect(option)) {
            return Answer.CORRECT_ANSWER;
        } else {
            return Answer.WRONG_ANSWER;
        }
    }

    @PostMapping(path = "/api/quizzes")
    @ResponseBody
    public Question createQuestion(@RequestBody Question postedQuestion) {
        System.out.println(postedQuestion);
        repository.save(postedQuestion);
        return repository.findLast();
    }

    @GetMapping(path = "/api/quizzes")
    public List<Question> showAllQuestions() {
        return repository.findAll();
    }

    @GetMapping(path = "/api/quizzes/{id}")
    public Question showQuestionById(@PathVariable Long id) {
        return repository.findById(id);
    }



}
