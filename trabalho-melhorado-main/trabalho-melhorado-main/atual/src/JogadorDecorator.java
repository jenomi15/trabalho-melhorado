public abstract class JogadorDecorator implements JogadorBase {
    protected JogadorBase jogadorDecorado;

    public JogadorDecorator(JogadorBase jogadorDecorado) {
        this.jogadorDecorado = jogadorDecorado;
    }

    @Override
    public int getMoedasCasaSimples() {
        return jogadorDecorado.getMoedasCasaSimples(); // Delegação para o objeto decorado
    }

    @Override
    public String getDescricao() {
        return jogadorDecorado.getDescricao(); // Delegação para o objeto decorado
    }

    @Override
    public void adicionarMoedas(JogadorBase jogador, int quantidade) {
        jogadorDecorado.adicionarMoedas(jogador, quantidade); // Delegação para o objeto decorado
    }

    @Override
    public int getPontuacaoEmMoedas() {
        return jogadorDecorado.getPontuacaoEmMoedas(); // Delegação para o objeto decorado
    }

    @Override
    public void removerMoeda(int quantidade) {
        jogadorDecorado.removerMoeda(quantidade); // Delegação para o objeto decorado
    }
}
