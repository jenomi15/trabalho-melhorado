 import java.util.ArrayList;
import java.util.List;

public class CasaSorte extends Casa {

    private List<Jogador> jogadores;
    private Tabuleiro tabuleiro; // Referência ao tabuleiro

    public CasaSorte(int numero, List<Jogador> jogadores, List<Casa> tabuleiroJogado) {
        super(numero);
        this.jogadores = jogadores;
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        // Verifica se o jogador não é um JogadorAzarado
        if (!(jogador instanceof JogadorAzarado)) {
            // O jogador anda 3 casas para frente
            int novaPosicao = jogador.getNumeroCasa() + 3;
            int totalDeCasas = tabuleiro.getNumeroDeCasas(); // Obtém o número total de casas do tabuleiro

            if (novaPosicao >= totalDeCasas) {
                novaPosicao = totalDeCasas - 1; // Limita a posição ao máximo permitido
            }
            jogador.setNumeroCasa(novaPosicao);
            System.out.println("O jogador " + jogador.getCor() + " andou 3 casas para frente e está agora na casa " + novaPosicao);
        } else {
            System.out.println("O jogador " + jogador.getCor() + " é um Jogador Sortudo e não se move.");
        }
    }
    public String toString() {
        return "Casa do tipo Sorte " +  (getNumero()-1)  + " -> Cores: " + cores;
        
        // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
    }
}