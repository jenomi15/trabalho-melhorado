import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private Tabuleiro tabuleiro;

    public void config(int numJogadores) {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Digite a cor do " + (i + 1) + "º jogador: ");
            String cor = teclado.nextLine();

            System.out.println("Digite o tipo do jogador (azarado, normal, sortudo): ");
            String tipo = teclado.nextLine();

            Jogador jogador = JogadorFactory.criarJogador(tipo, cor, 0, 0, 0, 0);
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
