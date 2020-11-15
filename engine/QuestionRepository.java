package engine;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/*Repository for saving in memory*/
//@Repository
public class QuestionRepository {

    private Long idSequence = 1L;
    private List<Question> allQuestions = new ArrayList<>();

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public QuestionRepository() {

    }

    public synchronized List<Question> findAll() {
        List<Question> copyOfAllQuestions = new ArrayList<>();
        for (Question question : allQuestions) {
            copyOfAllQuestions.add(clone(question));
        }
        return copyOfAllQuestions;
    }


    public synchronized Question findById(Long id) {
        int index = findIndexOfRecord(id);
        if (index == -1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return clone(allQuestions.get(index));
    }


    public synchronized Question findLast() {

        return clone(allQuestions.get(allQuestions.size()-1));
    }

    public synchronized void save(Question recordToSave) {
        if (recordToSave.getId() != null) {
            int index = findIndexOfRecord(recordToSave.getId());
            if (index != -1) {
                allQuestions.set(index, clone(recordToSave));
                return;
            }
        }
        recordToSave.setId(idSequence++);
        allQuestions.add(clone(recordToSave));

    }

    public synchronized void deleteById(Long id) {

    }
    ///////////////////////////////////////////////////

    private Question clone(Question originalQuestion) {
        return new Question(originalQuestion.getId(),
                originalQuestion.getTitle(),
                originalQuestion.getText(),
                originalQuestion.getOptions(),
                originalQuestion.getAnswer());

    }

    private int findIndexOfRecord(Long id) {
        for (int i = 0; i < allQuestions.size(); i++) {
            Question question = allQuestions.get(i);
            if (question.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }


}
