import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Jogo {
    private Tabuleiro tabuleiro;

    public void config(int numJogadores) {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);

        // Criando um mapa para controlar o número de jogadores por tipo
        Map<String, Integer> contagemPorTipo = new HashMap<>();
        contagemPorTipo.put("azarado", 0);
        contagemPorTipo.put("normal", 0);
        contagemPorTipo.put("sortudo", 0);

        // Calculando o limite de jogadores por tipo
        int limitePorTipo = numJogadores / 3;
        if (numJogadores % 3 != 0) {
            limitePorTipo++; // Se não for divisível por 3, aumenta o limite em 1
        }

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Digite a cor do " + (i + 1) + "º jogador: ");
            String cor = teclado.nextLine();

            String tipo = "";
            boolean tipoValido = false;

            // Repete a pergunta até o jogador selecionar um tipo válido com limite não excedido
            while (!tipoValido) {
                System.out.println("Digite o tipo do jogador (azarado, normal, sortudo): ");
                tipo = teclado.nextLine().toLowerCase();

                if (contagemPorTipo.containsKey(tipo) && contagemPorTipo.get(tipo) < limitePorTipo) {
                    tipoValido = true; // Tipo válido e limite ainda não excedido
                    contagemPorTipo.put(tipo, contagemPorTipo.get(tipo) + 1); // Atualiza a contagem
                } else {
                    System.out.println("Você atingiu o limite de jogadores do tipo " + tipo + ". Escolha outro tipo.");
                }
            }

            // Criação do jogador
            Jogador jogador = JogadorFactory.criarJogador(tipo, cor, 0, 0, 0, 0, false, false, false);
            jogadores.add(jogador);
        }

        tabuleiro = Tabuleiro.getInstancia(jogadores);
        tabuleiro.adicionarJogadores(jogadores);
    }

    public void configTabuleiro(int numCasas) {
        tabuleiro.criacaoDoTabuleiro1(numCasas);
    }

    public void printTabuleiro() {
        tabuleiro.imprimirTabuleiro();
    }


    public void start() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Selecione o modo de jogo: ");
        System.out.println("1 - Modo Normal");
        System.out.println("2 - Modo Debug");
        int opcao = teclado.nextInt();

        if (opcao == 1) {
            System.out.println("Iniciando o jogo no modo normal...");
            tabuleiro.TurnoDoJogo();
            
        } else if (opcao == 2) {
            System.out.println("Iniciando o jogo no modo debug...");
            tabuleiro.TurnoDoJogoDebug();
            
        } else {
            System.out.println("Opção inválida. Iniciando no modo normal por padrão...");
            tabuleiro.TurnoDoJogo();
           
        }
    }
}
