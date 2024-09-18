public class OculosEscuros extends JogadorDecorator {
    public OculosEscuros(JogadorBase jogadorDecorado) {
        super(jogadorDecorado);
    }

    @Override
    public int getMoedasCasaSimples() {
        return jogadorDecorado.getMoedasCasaSimples() + 3;
    }

    @Override
    public String getDescricao() {
        return jogadorDecorado.getDescricao() + ", com óculos escuros";
    }
}
