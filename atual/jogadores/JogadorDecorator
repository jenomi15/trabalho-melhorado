public abstract class JogadorDecorator implements JogadorBase { //abstrato ???//
    protected JogadorBase jogadorDecorado;

    public JogadorDecorator(JogadorBase jogadorDecorado) {
        this.jogadorDecorado = jogadorDecorado;
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
