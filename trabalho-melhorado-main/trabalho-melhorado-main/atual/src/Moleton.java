public class Moleton extends JogadorDecorator {
    public Moleton(JogadorBase jogadorDecorado) {
        super(jogadorDecorado);
    }

    @Override
    public int getMoedasCasaSimples() {
        return jogadorDecorado.getMoedasCasaSimples() + 2;
    }

    @Override
    public String getDescricao() {
        return jogadorDecorado.getDescricao() + ", com moletom";
    }
}
