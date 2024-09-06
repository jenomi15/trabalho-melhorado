public class CasaSimples extends Casa {
    public CasaSimples(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        jogador.pontuacaoEmModedas += 1;
        System.out.println("Casa Simples: 1 moeda adicionada ao jogador " + jogador.getNome() + ". Pontuação atual: " + jogador.pontuacaoEmModedas);
    }
}
