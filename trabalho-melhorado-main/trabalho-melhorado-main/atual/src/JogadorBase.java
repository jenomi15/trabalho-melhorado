// interface para o decorator dos acessórios//
public interface JogadorBase {
    int getMoedasCasaSimples();
    String getDescricao();
    void adicionarMoedas(JogadorBase jogador, int moedasCasaSimples);
    int getPontuacaoEmMoedas();
    void removerMoeda(int preco);
    void setPontuacaoEmMoedas(int pontuacaoEmMoedas);
}
