import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

/*
a) Deverão ser passadas as seguintes informações para o cálculo do total da compra:
nome do produto, valor unitário e quantidade comparada;
b) Se pago à vista: desconto de 5% no total da compra,
neste caso, o programa deverá calcular o troco do cliente.
c) Se pago no débito: valor total sem acréscimo nem desconto;
d) Se pago no crédito: acréscimo de 1% no total da compra;
e) O arquivo gerado deverá se chamar totalPedido.txt 
*/

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
		System.out.println("\n* Menu Principal > Pedido *");
		System.out.println("* 1 - Inserir produto     *");
		System.out.println("* 2 - Alterar produto     *");
		System.out.println("* 3 - Excluir produto     *");
		System.out.println("* 4 - Encerrar pedido     *");
		System.out.println("");
		System.out.print("* Opção: ");
		return entrada.nextInt();
	}

	public static int mostrarFormasPagamento() {
		System.out.println("\n** Menu Principal > Receber ***");
		System.out.println("* 1 - Dinheiro          (-5%) *");
		System.out.println("* 2 - Cartão de Débito        *");
		System.out.println("* 3 - Cartão de Crédito (+1%) *");
		System.out.println("* 4 - Retornar ao menu        *");
		System.out.println("");
		System.out.print("* Opção: ");
		return entrada.nextInt();
	}

	public static void mostrarCaixa(int idPagamento) {
		String descPagamento = "";
		double totalPedido = calcularTotalPedido();
		double valorPago = 0;
		double descontoAcrescimo = 0;
		switch (idPagamento) {
			case 1:
				descPagamento = "Dinheiro (-5%)";
				descontoAcrescimo = 0.95;
				break;
			case 2:
				descPagamento = "Cartão de Débito";
				descontoAcrescimo = 1;
				break;
			case 3:
					descPagamento = "Cartão de Crédito (+1%)";
					descontoAcrescimo = 1.01;
					break;
			default:
				break;
		}
		System.out.println("\n** Menu Principal > Receber > Caixa");
		System.out.println("* Forma de pagamento: " + descPagamento);
		System.out.printf("*       Total Pedido: R$ %10.2f\n", totalPedido);
		System.out.printf("*       Total Líqudo: R$ %10.2f\n", (totalPedido * descontoAcrescimo));
		
		do {
			System.out.print("*         Valor pago: R$       ");
			valorPago = entrada.nextDouble();
			if (valorPago < (totalPedido * descontoAcrescimo)) {
				System.out.println("O valor pago não pode ser inferior ao valor líquido.");
			}
		} while (valorPago < (totalPedido * descontoAcrescimo));
		
		System.out.printf("*              Troco: R$ %10.2f\n", (((totalPedido * descontoAcrescimo) - valorPago) * -1));
	
		System.out.print("Deseja realmente fechar o pedido? (s/n): ");
		String fecharPedido = entrada.next();

		if (fecharPedido.equals("s")) {
			System.out.println("Pedido fechado com sucesso com sucesso.");
			System.out.print("\nPressione qualquer tecla para imprimir o comprovante...");
			entrada.next();
			imprimirPedido(descPagamento, totalPedido, valorPago, descontoAcrescimo);
			
			int quantidadeProdutos = produtos.size(); 
			for(int i = 0; i < quantidadeProdutos; i++){
				produtos.remove(0);
				valorUnitario.remove(0);
				quantidade.remove(0);
			}
		} else {
			System.out.println("Operação cancelada. O pedido não foi fechado.");
			System.out.print("\nPressione qualquer tecla para continuar...");
			entrada.next();
		}

	}

	public static void imprimirPedido(String descPagamento, double totalPedido, double valorPago, double descontoAcrescimo) {
		try {
			FileWriter dirArquivo = new FileWriter("totalPedido.txt", true);
			PrintWriter arquivo = new PrintWriter(dirArquivo);
			arquivo.println("\n*************************** RESUMO DO PEDIDO ***************************");
			arquivo.println("\n************************************************************************");
			arquivo.println("* Indice | Descrição                      | Vlr. Unitário | Quantidade *");
			for(int i = 0; i < produtos.size(); i++){
				arquivo.printf("* %6d | %-30S | R$ %10.2f | %10.2f *\n", i+1, produtos.get(i), valorUnitario.get(i), quantidade.get(i));
			}
			arquivo.println("*                                                                      *");
			arquivo.println("************************************************************************");
			arquivo.println("");
			arquivo.println("************************************************************************");
			arquivo.printf("*                                Forma de pagamento: %-17S *\n", descPagamento);
			arquivo.printf("*                                      Total Pedido: R$ %14.2f *\n", totalPedido);
			arquivo.printf("*                                      Total Líqudo: R$ %14.2f *\n", (totalPedido * descontoAcrescimo));
			arquivo.printf("*                                        Valor pago: R$ %14.2f *\n", valorPago);
			arquivo.printf("*                                             Troco: R$ %14.2f *\n", ((totalPedido * descontoAcrescimo) - valorPago));
			arquivo.println("************************************************************************");

			arquivo.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static double calcularTotalPedido() {
		double soma = 0;
		for(int i = 0; i < produtos.size(); i ++){
			soma += valorUnitario.get(i) * quantidade.get(i);
		}
		return soma;
	}

	public static void inserirProduto(){
		System.out.println("");
		System.out.println("********** Inserindo produto ***********");
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
		System.out.print("Digite o indice do produto a ser alterado: ");
		int indice = entrada.nextInt() - 1;

		System.out.println("\nAlterando o produto:");
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
		String nomeProduto = produtos.get(indice);
		produtos.remove(indice);
		valorUnitario.remove(indice);
		quantidade.remove(indice);
		System.out.printf("\nO produto %d - %S foi excluído com sucesso.\n", indice + 1, nomeProduto);
	}

	public static void excluirPedido(){
		System.out.println("");
		System.out.println("O pedido será excluído.");
		System.out.println("");
		listarProdutos();

		System.out.print("Deseja realmente excluir o pedido selecionado? (s/n): ");
		String excluirPedido = entrada.next();

		if (excluirPedido.equals("s")) {
			for(int i = 0; i < produtos.size(); i++){
				produtos.remove(0);
				valorUnitario.remove(0);
				quantidade.remove(0);
			}
			System.out.println("Pedido removido com sucesso.");
		} else {
			System.out.println("Operação cancelada. O pedido não foi excluído.");
		}

		System.out.print("\nPressione qualquer tecla para continuar...");
		entrada.next();	
	}

	public static void main(String[] args) {
		int opcaoMenuPrincipal;
		do {
			clearScreen();
			opcaoMenuPrincipal =  mostrarMenuPrincipal();

			switch (opcaoMenuPrincipal) {
				case 1:
					int opcaoMenuPedido;
					do {
						clearScreen();
						if (produtos.size() > 0) {
							listarProdutos();
						}
						opcaoMenuPedido = mostrarMenuPedido();
						switch (opcaoMenuPedido) {
							case 1:
								inserirProduto();
								listarProdutos();
								break;
							case 2:
								clearScreen();
								listarProdutos();
								alterarProduto();
								break;
							case 3:
								clearScreen();
								listarProdutos();
								excluirProduto();
								break;
							default:
								System.out.println("Opção inválida.");
								break;
						}
					} while (opcaoMenuPedido != 4);
					break;
				case 2:
					excluirPedido();
					break;
				case 3:
					clearScreen();
					int opcaoFormasPagamento = mostrarFormasPagamento();
					mostrarCaixa(opcaoFormasPagamento);
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