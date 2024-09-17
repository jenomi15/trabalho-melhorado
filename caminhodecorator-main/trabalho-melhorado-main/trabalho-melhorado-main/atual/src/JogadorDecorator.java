public abstract class JogadorDecorator implements JogadorBase {
    protected JogadorBase jogadorDecorado;

    public JogadorDecorator(JogadorBase jogadorDecorado) {
        this.jogadorDecorado = jogadorDecorado;
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        jogadorDecorado.adicionarMoedas(quantidade);
    }

    @Override
    public int getMoedasCasaSimples() {
        return jogadorDecorado.getMoedasCasaSimples();
    }

    @Override
    public String getDescricao() {
        return jogadorDecorado.getDescricao();
    }
}
