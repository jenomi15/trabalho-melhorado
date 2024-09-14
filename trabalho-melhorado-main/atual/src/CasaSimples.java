import java.util.ArrayList;

public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        int moedas = jogador.calcularMoedasCasaSimples();
        jogador.adicionarMoeda(moedas);
        System.out.println("Casa Simples: " + moedas + " moedas adicionadas ao jogador " + jogador.getNome() + ". Pontuação atual: " + jogador.getPontuacaoEmMoedas());
    }
}
