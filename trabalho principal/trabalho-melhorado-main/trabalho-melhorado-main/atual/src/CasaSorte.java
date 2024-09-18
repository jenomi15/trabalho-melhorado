import java.util.ArrayList;
import java.util.List;

public class CasaSorte extends Casa {

    private List<Jogador> jogadores;
    private List<Casa> tabuleiroJogado; // Referência ao tabuleiro

    public CasaSorte(int numero, List<Jogador> jogadores, List<Casa> tabuleiroJogado) {
        super(numero);
        this.jogadores = jogadores;
        this.tabuleiroJogado = tabuleiroJogado; // Recebe o tabuleiro jogado como ArrayList
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        if ((jogador instanceof JogadorSortudo) || (jogador instanceof JogadorNormal)) {
            int novaPosicao = jogador.getNumeroCasa() + 3;
            int totalDeCasas = tabuleiroJogado.size(); 

            if (novaPosicao >= totalDeCasas) {
                novaPosicao = totalDeCasas - 1; // Limita a posição ao máximo permitido
            }
            
            jogador.setNumeroCasa(jogador.getNumeroCasa()+3);
            
            System.out.println("O jogador " + jogador.getCor() + " andou 3 casas para frente e está agora na casa " + novaPosicao);
        } else {
            System.out.println("O jogador " + jogador.getCor() + " é um Jogador Azarado e não se move.");
        }
        
    }

    @Override
    public String toString() {
        return "Casa do tipo Sorte " + getNumero()  + " -> Cores: " + cores;
    }
}  