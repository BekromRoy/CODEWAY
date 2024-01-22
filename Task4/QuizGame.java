import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOption;

    public QuizQuestion(String question, List<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.timer = new Timer();
    }

    public void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        askQuestion();
    }

    private void askQuestion() {
        if (currentQuestionIndex < questions.size()) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);

            // Set up a timer for each question
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    currentQuestionIndex++;
                    askQuestion();
                }
            }, 10000); // 10 seconds timer

            handleUserInput(currentQuestion);
        } else {
            endQuiz();
        }
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println("\n" + question.getQuestion());
        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private void handleUserInput(QuizQuestion question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your choice (1-" + question.getOptions().size() + "): ");
        int userChoice = scanner.nextInt();

        if (userChoice == question.getCorrectOption()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct option : " + question.getCorrectOption());
        }

        currentQuestionIndex++;
        askQuestion();
    }

    private void endQuiz() {

        timer.cancel(); // Cancel any remaining timers
        System.out.println("\nQuiz ended. Your final score: " + score + " out of " + questions.size());

        // Display a summary of correct and incorrect answers
        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            System.out.println("Q" + (i + 1) + ": " + question.getQuestion());
            System.out.println("Your answer: " + question.getOptions().get(question.getCorrectOption() - 1));
        }
    }
}

public class QuizGame {
    public static void main(String[] args) {

        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of India?",
                List.of("Kolkata", "Delhi", "Kanpur", "Goa"), 2));
        questions.add(new QuizQuestion("Name the famous Bollywood actor known as the 'Shahenshah' of Bollywood.",
                List.of("Amitabh Bachchan", "Sharukh Khan", "Kishor Kumar", "Tiger Shorff"), 1));
        questions.add(new QuizQuestion("What is the national sport of India?",
                List.of("Cricket", "Hockey", "Chess", "Football"), 2));
        questions.add(new QuizQuestion("Who is considered the father of the Indian space program?",
                List.of("Dr. Vikram Sarabhai", "Srinivasa Ramanujan", "Vishwanathan Anand", "Slumdog Millionaire"), 1));
        questions.add(new QuizQuestion("Name the classical dance form that originated in the southern state of Kerala.",
                List.of("Salsa", "Hip Hop", "Kathakali", "Odissi"), 3));

        // Create and start the quiz
        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
