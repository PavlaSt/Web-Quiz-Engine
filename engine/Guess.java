package engine;

import java.util.Arrays;

public class Guess {
    int[] answer;

    @Override
    public String toString() {
        return "Guess{" +
                "answer=" + Arrays.toString(answer) +
                '}';
    }

    public int[] getAnswer() {
        return answer;
    }
}
