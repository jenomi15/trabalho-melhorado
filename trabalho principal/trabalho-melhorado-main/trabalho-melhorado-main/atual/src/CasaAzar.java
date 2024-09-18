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
    @Override
public String toString() {
    return "Casa do tipo Azar " +  getNumero()  + " -> Cores: " + cores;
    
    // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
}
}
