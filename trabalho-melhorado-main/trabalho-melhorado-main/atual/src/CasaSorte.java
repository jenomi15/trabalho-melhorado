import java.util.ArrayList;
import java.util.List;

public class CasaSorte extends Casa {

    private List<Jogador> jogadores;

    public CasaSorte(int numero, List<Jogador> jogadores) {
        super(numero);
        this.jogadores = jogadores;
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        // Verifica se o jogador não é um JogadorSortudo
        if (!(jogador instanceof JogadorSortudo)) {
            // O jogador anda 3 casas para frente
            int novaPosicao = jogador.getNumeroCasa() + 3;
            if (novaPosicao > 39) {
                novaPosicao = 39; // Limita a posição ao máximo permitido
            }
            jogador.setNumeroCasa(novaPosicao);
            System.out.println("O jogador " + jogador.getCor() + " andou 3 casas para frente e está agora na casa " + novaPosicao);
        } else {
            System.out.println("O jogador " + jogador.getCor() + " é um Jogador Sortudo e não se move.");
        }
    }
}
