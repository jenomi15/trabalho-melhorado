import java.util.ArrayList;

public class CasaAzar extends Casa {

    private static final int PERDA_MOEDAS = 3;

    public CasaAzar(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        if (jogador.getTipo() != 3) { // 3 representa JogadorSortudo
            jogador.removerMoeda(PERDA_MOEDAS);
            System.out.println("Casa Azar: O jogador " + jogador.getCor() + " perdeu " + PERDA_MOEDAS + " moedas.");
        } else {
            System.out.println("Casa Azar: O jogador " + jogador.getCor() + " é Sortudo e não perdeu moedas.");
        }
    }
}
