import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("                                                      BEM VINDO AO JOGO DE TABULEIRO\n Digite quantos jogadores você quer criar: ");
        int j = teclado.nextInt();
        while (j < 1 || j > 6) {
            System.out.println("Número de jogadores inválido. Por favor, selecione um número de jogadores entre 1 e 6.");
            j = teclado.nextInt();
        }
        ArrayList<Jogador> jogadores = new ArrayList<>();
        teclado.nextLine();

        for (int i = 0; i < j; i++) {
            System.out.println("Digite a cor do " + (i+1)  + "º jogador: ");
            String cor = teclado.nextLine();

            Jogador jogador = new Jogador(cor , 0 , false);

            jogadores.add(jogador);
        }

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println("Jogador " + (i + 1) + " - Cor: " + jogador.getCor());
        }

        Tabuleiro tabuleiro = new Tabuleiro(jogadores);
        tabuleiro.criacaoDoTabuleiro(40);

        for (int l = 0; l < j; l++) {
            tabuleiro.adicionarCorCasa(0, jogadores.get(l).getCor());
        }

        tabuleiro.imprimirTabuleiro();
        

       tabuleiro.TurnoDoJogo();
       teclado.close();
       
        
    }
}

        
        
        

        
 






