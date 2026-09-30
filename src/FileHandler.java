import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class FileHandler {

    public static List<GradeBook> readGradeBooks(String filename) throws IOException {
        Map<String, GradeBook> studentMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split(";");
                if (tokens.length < 7) continue;

                String fio = tokens[0].trim();
                int course = Integer.parseInt(tokens[1].trim());
                String group = tokens[2].trim();
                int sessionNum = Integer.parseInt(tokens[3].trim());
                String subject = tokens[4].trim();
                String type = tokens[5].trim();
                String grade = tokens[6].trim();

                studentMap.putIfAbsent(fio, new GradeBook(fio, course, group));
                studentMap.get(fio).addRecord(sessionNum, subject, type, grade);
            }
        }
        return new ArrayList<>(studentMap.values());
    }

    public static void writeExcellentStudents(String filename, List<GradeBook> books) throws IOException {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            for (GradeBook book : books) {
                if (book.isExcellentStudent()) {
                    for (GradeBook.Session s : book.getSessions()) {
                        for (int i = 0; i < s.getAverageGrade(); i++) {
                        }
                        pw.printf("%s, %d курс, группа %s, сессия %d\n",
                                book.getStudentFio(), book.getCourse(), book.getGroup(), s.getSessionNumber());
                    }
                }
            }
        }
    }

    public static void writeProgressingStudents(String filename, List<GradeBook> books) throws IOException {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            for (GradeBook book : books) {
                boolean progressing = true;
                for (GradeBook.Session s : book.getSessions()) {
                    if (s.hasFailures()) {
                        progressing = false;
                        break;
                    }
                }

                if (progressing) {
                    pw.println("Студент: " + book.getStudentFio() + " (Группа: " + book.getGroup() + ")");
                    for (GradeBook.Session s : book.getSessions()) {
                        pw.printf("  Сессия %d - Средний балл: %.2f\n", s.getSessionNumber(), s.getAverageGrade());
                    }
                    pw.println();
                }
            }
        }
    }
}
