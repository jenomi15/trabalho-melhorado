import java.util.Scanner;

public class Facade {

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        Scanner teclado = new Scanner(System.in);

      
        System.out.println("Digite o número de casas no tabuleiro: ");
        int numCasas = teclado.nextInt();

       
        System.out.println("Digite o número de jogadores: "); 
        int numJogadores = teclado.nextInt();

        jogo.config(numJogadores); 
        jogo.configTabuleiro(numCasas); 

        
        jogo.printTabuleiro();

        
        jogo.start();

        teclado.close();
    }
}