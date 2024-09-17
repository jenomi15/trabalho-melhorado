import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        



        System.out.println("                                                      BEM VINDO AO JOGO DE TABULEIRO\nDigite quantos jogadores você quer criar: ");
        int j = teclado.nextInt();
        while (j < 1 || j > 6) {
            System.out.println("Número de jogadores inválido. Por favor, selecione um número de jogadores entre 1 e 6.");
            j = teclado.nextInt();
        }
        ArrayList<Jogador> jogadores = new ArrayList<>();
        teclado.nextLine();  

        for (int i = 0; i < j; i++) {
            System.out.println("Digite a cor do " + (i + 1) + "º jogador: ");
            String cor = teclado.nextLine();

            System.out.println("Digite o tipo do jogador (azarado, normal, sortudo): ");
            String tipo = teclado.nextLine();

            // Utiliza a Factory para criar o jogador do tipo fornecido
            Jogador jogador = JogadorFactory.criarJogador(tipo, cor, 0, 0, 0, 0);

            jogadores.add(jogador);
        }
    

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println("Jogador " + (i + 1) + " - Cor: " + jogador.getCor() + " - Tipo: " + jogador.getClass().getSimpleName());
        }

       // System.out.println("Digite a quantidade de casas no tabuleiro: ");
        //int quantidade = teclado.nextInt(); 
        
        // pega a instância única do Tabuleiro usando o Singleton
        Tabuleiro tabuleiro = Tabuleiro.getInstancia(jogadores); 
        tabuleiro.criacaoDoTabuleiro();

        for (int l = 0; l < j; l++) {
            tabuleiro.adicionarCorCasa(0, jogadores.get(l).getCor());
        }

        tabuleiro.imprimirTabuleiro();

       
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

        teclado.close();
    }
}

