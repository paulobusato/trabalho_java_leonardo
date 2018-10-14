import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Atividade01 {
	public static Scanner entrada = new Scanner(System.in).useDelimiter("\\n");
	public static ArrayList<String> produtos = new ArrayList<>();
	public static ArrayList<Double> valorUnitario = new ArrayList<>();
	public static ArrayList<Double> quantidade = new ArrayList<>();

	public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
	}  

	public static int mostrarMenuPrincipal() {
		if (produtos.size() == 0) {
			System.out.println("\n*** Menu Principal ***");
			System.out.println("* 1 - Iniciar pedido *");
			System.out.println("* 4 - Sair           *");
		} else {
			System.out.println("\n**** Menu Principal ****");
			System.out.println("* 1 - Continuar pedido *");
			System.out.println("* 2 - Excluir pedido   *");
			System.out.println("* 3 - Receber          *");
			System.out.println("* 4 - Sair             *");
		}
		System.out.println("");
		System.out.print("* Opção: ");
		return entrada.nextInt();
	}

	public static int mostrarMenuPedido() {
		System.out.println("\n***** Menu Pedido *****");
		System.out.println("* 1 - Inserir produto *");
		System.out.println("* 2 - Alterar produto *");
		System.out.println("* 3 - Excluir produto *");
		System.out.println("* 4 - Encerrar pedido *");
		System.out.println("");
		System.out.print("* Opção: ");
		return entrada.nextInt();
	}

	public static void inserirProduto(){
		System.out.println("");
		System.out.println("*********** Inserindo produto ***********");
		System.out.print("    Descrição: ");
		produtos.add(entrada.next());
		System.out.print("Vlr. Unitário: ");
		valorUnitario.add(entrada.nextDouble());
		System.out.print("   Quantidade: ");
		quantidade.add(entrada.nextDouble());
	}

	public static void listarProdutos(){
		System.out.println("\n************************************************************************");
		System.out.println("* Indice | Descrição                      | Vlr. Unitário | Quantidade *");
		for(int i = 0; i < produtos.size(); i++){
			System.out.printf("* %6d | %-30S | R$ %10.2f | %10.2f *\n", i+1, produtos.get(i), valorUnitario.get(i), quantidade.get(i));
		}
		System.out.println("*                                                                      *");
		System.out.println("************************************************************************");
		System.out.println("");
	}

	public static void alterarProduto() {
		listarProdutos();

		System.out.print("Digite o indice do produto a ser alterado: ");
		int indice = entrada.nextInt() - 1;
		
		System.out.println("\nProduto que será alterado");
		System.out.println("\n************************************************************************");
		System.out.println("* Indice | Descrição                      | Vlr. Unitário | Quantidade *");
		System.out.printf("* %6d | %-30S | R$ %10.2f | %10.2f *\n", indice+1, produtos.get(indice), valorUnitario.get(indice), quantidade.get(indice));
		System.out.println("*                                                                      *");
		System.out.println("************************************************************************");
		System.out.println("");

		System.out.println("*********** Alterando produto ***********");
		System.out.print("    Descrição: ");
		produtos.set(indice, entrada.next());
		System.out.print("Vlr. Unitário: ");
		valorUnitario.set(indice, entrada.nextDouble());
		System.out.print("   Quantidade: ");
		quantidade.set(indice, entrada.nextDouble());
	}

	public static void excluirProduto(){
		System.out.print("Digite o indice do produto a ser excluído: ");
		int indice = entrada.nextInt() - 1;
		produtos.remove(indice);
		valorUnitario.remove(indice);
		quantidade.remove(indice);
		System.out.printf("\nO produto %d - %S foi excluído com sucesso.\n", indice + 1, produtos.get(indice));
	}

	public static void excluirPedido(){
		System.out.println("");
		System.out.println("O pedido será excluído.");
		System.out.println("");
		listarProdutos();
		
		for(int i = 0; i < produtos.size(); i++){
			produtos.remove(0);
			valorUnitario.remove(0);
			quantidade.remove(0);
		}

		System.out.println("Produto removido com sucesso.");
	}

	public static void main(String[] args) {
		int opcaoMenuPrincipal;
		do {
			opcaoMenuPrincipal =  mostrarMenuPrincipal();

			switch (opcaoMenuPrincipal) {
				case 1:
					int opcaoMenuPedido;
					do {
						if (produtos.size() > 0) {
							listarProdutos();
						}
						opcaoMenuPedido = mostrarMenuPedido();
						switch (opcaoMenuPedido) {
							case 1:
								inserirProduto();
								break;
							case 2:
								alterarProduto();
								break;
							case 3:
								listarProdutos();
								excluirProduto();
								break;
							default:
								break;
						}
					} while (opcaoMenuPedido != 4);
					break;
				case 2:
						excluirPedido();
					break;
				case 3:
					break;
				case 4:
					System.out.println("Saindo do sistema...");
					break;
				default:
					System.out.println("Opção inválida.");
					break;
			}
		} while (opcaoMenuPrincipal != 4);
	}
}