import modelos.Moeda;
import modelos.Conversao;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int sair = 0;
    while (sair != 9) {
      // interface textual que apresenta opções de conversão de moedas.
      String menu = """
        *****************************************
        Seja bem-vindo/a ao Conversor de Moeda =]

        1) Dólar =>> Peso argentino
        2) Peso argentino =>> Dólar
        3) Dólar =>> Real brasileiro
        4) Real brasileiro =>> Dólar
        5) Dólar =>> Peso colombiano
        6) Peso colombiano =>> Dólar
        7) Dólar ==> Peso uruguaio
        8) Peso uruguaio ==> Dólar
        9) Sair
        Escolha uma opção válida:
        *****************************************

        """;

      System.out.println(menu);

      int entrada = scanner.nextInt();

      switch (entrada) {
        case 1:
        /* O segundo argumento recebe o "Currency Code" da moeda de origem, enquanto o
           terceiro argumento recebe o "Currency Code" da moeda de destino */
        converterMoedas(scanner, "USD", "ARS");
        break;
        case 2:
        converterMoedas(scanner, "ARS", "USD");
        break;
        case 3:
        converterMoedas(scanner, "USD", "BRL");
        break;
        case 4:
        converterMoedas(scanner, "BRL", "USD");
        break;
        case 5:
        converterMoedas(scanner, "USD", "COP");
        break;
        case 6:
        converterMoedas(scanner, "COP", "USD");
        break;
        case 7:
        converterMoedas(scanner, "USD", "UYU");
        break;
        case 8:
        converterMoedas(scanner, "UYU", "USD");
        break;
        case 9:
        sair = 9;
        break;
        default:
        System.out.println("Opção inválida!");
        break;
      }
    }
  }

  public static void converterMoedas(Scanner scanner, String moedaOrigem, String moedaDestino) {

    System.out.println("Digite o valor que deseja converter: ");

    // A variável armazena os valores inseridos pelo usuário.
    double valor = scanner.nextDouble();

    Conversao conversao = new Conversao(valor);

    Moeda moeda = new Moeda(moedaOrigem, moedaDestino);

    conversao.pegaMoeda(moeda);

    double valorConvertido = conversao.getValorConvertido();

    System.out.println(String.format("Valor %.2f [%s] corresponde ao valor final de =>>> %.2f [%s]", valor, moedaOrigem, valorConvertido, moedaDestino));
  }
}
