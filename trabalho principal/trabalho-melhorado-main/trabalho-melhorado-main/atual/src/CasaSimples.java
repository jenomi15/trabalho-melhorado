public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        int moedas = jogador.getMoedasCasaSimples(); // Use o método atualizado para calcular as moedas
        jogador.adicionarMoeda(moedas);
        System.out.println("Casa Simples: " + moedas + " moedas adicionadas ao jogador " + jogador.getCor() + ". Pontuação atual: " + jogador.getPontuacaoEmMoedas());
    }
    



    @Override
    public String toString() {
        return "Casa do tipo Simples " + getNumero() + " -> Cores: " + cores;
    }
}
 



