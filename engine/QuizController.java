package engine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    private List<User> userList = new ArrayList<>();

    @GetMapping("/quizzes")
    public List<Question> getAllQuestions() {
        return quizRepository.findAll();
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable(value = "id") Long questionId)
    {
        Question question = quizRepository.findById(questionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok().body(question);
    }

    @PostMapping("/quizzes")
    public Question createQuestion(@Valid @RequestBody Question question) {
        return quizRepository.save(question);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userList.add(user);

        return ResponseEntity.ok().body("OK");
    }



    @PostMapping(path = "/quizzes/{id}/solve")
    public ResponseEntity<Answer> checkSolution(@PathVariable(value = "id") Long questionId,
                                                @RequestBody  Guess guess){
        System.out.println("checkSolution :" + questionId + "--"+ guess);
        Question question = quizRepository.findById(questionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        System.out.println("controller answer/option: " + guess);

        if(question.isCorrect(guess.getAnswer())) {
            return ResponseEntity.ok().body(Answer.CORRECT_ANSWER);
        } else {
            return ResponseEntity.ok().body(Answer.WRONG_ANSWER);
        }
    }



}
