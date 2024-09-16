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
        System.out.println("Casa Simples: " + moedas + " moedas adicionadas ao jogador " + jogador.getCor() + ". Pontuação atual: " + jogador.getPontuacaoEmMoedas());
    }
    public String toString() {
        return "Casa do tipo Simples " + (getNumero()-1)  + " -> Cores: " + cores;
        
        // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
    }

}
