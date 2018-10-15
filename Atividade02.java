import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Atividade02 {
	public static void main(String[] args) {
		try {
			FileWriter dirArquivo = new FileWriter("../nome.txt");
			PrintWriter arquivo = new PrintWriter(dirArquivo);
			arquivo.print("Helllo");
			arquivo.close();
		} catch (Exception e) {
			//TODO: handle exception
		}
	}
}