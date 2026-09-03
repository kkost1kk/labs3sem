import java.util.Locale;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        double x = 0;
        int k = 0;
        try{
            System.out.println("enter first number(x) from -1 to 1");
            x = scanner.nextDouble();
            System.out.println("enter power of accuracy(k)");
            k = scanner.nextInt();
            if(k < 0){
                System.out.println("enter positive number");
                return;
            }
            if (x <= -1 || x >= 1) {
                System.out.println("enter x from -1 to 1");
                return;
            }
        }catch(InputMismatchException e){
            return;
        }
        LogicClass.calculate(x, k);
    }
}
class LogicClass{
    public static void calculate(double x, int k) {
        double epsilon = Math.pow(10, -k);
        double sum = 0;
        double add = 1;
        int count = 1;
        while (Math.abs(add) >= epsilon) {
            sum += add;
            add = -add * x * (count * 2.0 - 1) / (count * 2.0);
            ++count;
        }
        double check = Math.sqrt(1.0 / (1.0 + x));
        System.out.println("our sum: " + sum);
        System.out.println("check: " + check);
    }
}