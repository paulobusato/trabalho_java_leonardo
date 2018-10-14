import java.util.Scanner;
import java.util.ArrayList;

public class Atividade02 {
    public static void main(String[] args) {
        ArrayList<Integer> inteiros = new ArrayList<>();

        inteiros.add(1);
        inteiros.add(2);
        inteiros.add(3);

        System.out.println(inteiros.remove(2));
        System.out.println(inteiros.remove(1));
        System.out.println(inteiros.remove(0));
    }
}