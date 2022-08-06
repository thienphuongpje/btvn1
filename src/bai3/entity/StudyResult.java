package bai3.entity;

public class StudyResult {
    private Subject subject;
    private int score;

    public StudyResult() {
        this.subject = new Subject();
        this.score = 0;
    }

    public StudyResult(Subject subject, int score) {
        this.subject = subject;
        this.score = score;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
