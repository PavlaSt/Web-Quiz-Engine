package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Objects;

public class Question {
    public static final Question QUESTION = new Question(1L, "The Java Logo",
            "What is depicted on the Java logo?",
            new String[]{"Robot", "Tea leaf", "Cup of coffee", "Bug"}, new int[]{2});

    private Long id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String text;
    @NotNull
    @Size(min = 2)
    private String[] options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int[] answer = null;

    public Question() {
    }

    public Question(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = new int[]{};
    }

    public Question(String title, String text, String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Question(Long id, String title, String text, String[] options, int[] answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options.clone();
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @JsonIgnore
    public int[] getAnswer() {
        int[] x = null;
        if (Objects.equals(x, answer)) {
            int[] ints = new int[0];
            return ints;
        }
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options.clone();
    }

    public boolean isCorrect(int[] option) {

        System.out.println("answer: " + Arrays.toString(answer));
        System.out.println("option: " + Arrays.toString(option));
        int[] x = null;
        if (Objects.equals(x, option)) {
            option = new int[0];
        }
        Arrays.sort(option);
        Arrays.sort(answer);


        return  Arrays.equals(answer, option); //answer.equals(option);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + Arrays.toString(options) +
                ", correct answer=" + answer +
                '}';
    }
}
