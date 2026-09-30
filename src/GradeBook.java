import java.util.ArrayList;
import java.util.List;

public class GradeBook {
    private String studentFio;
    private int course;
    private String group;
    private List<Session> sessions;

    public GradeBook(String studentFio, int course, String group) {
        this.studentFio = studentFio;
        this.course = course;
        this.group = group;
        this.sessions = new ArrayList<>();
    }

    public String getStudentFio() {
        return studentFio;
    }

    public int getCourse() {
        return course;
    }

    public String getGroup() {
        return group;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    // Метод добавления записи в нужную сессию
    public void addRecord(int sessionNumber, String subject, String type, String grade) {
        Session targetSession = null;
        for (Session s : sessions) {
            if (s.getSessionNumber() == sessionNumber) {
                targetSession = s;
                break;
            }
        }
        if (targetSession == null) {
            targetSession = new Session(sessionNumber);
            sessions.add(targetSession);
        }
        targetSession.addSubject(subject, type, grade);
    }

    public boolean isExcellentStudent() {
        if (sessions.isEmpty()) return false;
        for (Session s : sessions) {
            for (int i = 0; i < s.subjects.size(); i++) {
                String type = s.types.get(i);
                String grade = s.grades.get(i);

                if (type.equalsIgnoreCase("Экзамен")) {
                    try {
                        int score = Integer.parseInt(grade);
                        if (score < 9) return false;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                } else if (type.equalsIgnoreCase("Зачет")) {
                    if (!grade.equalsIgnoreCase("Сдано")) return false;
                }
            }
        }
        return true;
    }

    public class Session {
        private int sessionNumber;
        private List<String> subjects = new ArrayList<>();
        private List<String> types = new ArrayList<>();
        private List<String> grades = new ArrayList<>();

        public Session(int sessionNumber) {
            this.sessionNumber = sessionNumber;
        }

        public int getSessionNumber() {
            return sessionNumber;
        }

        public void addSubject(String subject, String type, String grade) {
            subjects.add(subject);
            types.add(type);
            grades.add(grade);
        }

        public double getAverageGrade() {
            int sum = 0;
            int count = 0;
            for (int i = 0; i < subjects.size(); i++) {
                if (types.get(i).equalsIgnoreCase("Экзамен")) {
                    try {
                        sum += Integer.parseInt(grades.get(i));
                        count++;
                    } catch (NumberFormatException ignored) {
                    }
                }
            }
            return count == 0 ? 0.0 : (double) sum / count;
        }

        public boolean hasFailures() {
            for (int i = 0; i < subjects.size(); i++) {
                if (types.get(i).equalsIgnoreCase("Экзамен")) {
                    try {
                        if (Integer.parseInt(grades.get(i)) < 4) return true;
                    } catch (NumberFormatException e) {
                        return true;
                    }
                } else if (types.get(i).equalsIgnoreCase("Зачет")) {
                    if (!grades.get(i).equalsIgnoreCase("Сдано")) return true;
                }
            }
            return false;
        }
    }
}
