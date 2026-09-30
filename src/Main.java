import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer text = new StringBuffer();

        try {
            System.out.println("choose mode: 0(в каждой строке текста удалить указанный символ\n" +
                    "везде, где он встречается) " + "or 1(вставить его после k-гo символа): ");
            int n = Integer.parseInt(br.readLine().trim());
            if (n != 0 && n != 1) throw new IllegalArgumentException("must be 0 or 1.");

            System.out.println("enter a character: ");
            String input = br.readLine();
            if (input == null || input.isEmpty()) {
                throw new IllegalArgumentException("didnt enter a symbol.");
            }
            char c = input.charAt(0);
            int k = 0;
            if (n == 1) {
                System.out.println("enter k(position): ");
                k = Integer.parseInt(br.readLine().trim());
                if (k < 0) {
                    throw new IllegalArgumentException("position k cannot be negative.");
                }
            }

            System.out.print("enter your text(double enter to finish):\n");
            while (true) {
                String line = br.readLine();
                if (line == null || line.isEmpty()) break;

                StringBuffer processedLine;

                if (n == 0) {
                    processedLine = TaskProcessor.deleteSymbolsFromLine(c, line);
                } else {
                    processedLine = TaskProcessor.insertAfterKSymbol(c, line, k);
                }
                text.append(processedLine).append("\n");
            }

            System.out.println("results:");
            System.out.println(text.toString());

        } catch (NumberFormatException e) {
            System.out.println("must be an integer");
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("error of reading from keyboard");
        }
    }
}
