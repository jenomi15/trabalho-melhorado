public class Moleton extends JogadorDecorator {
    public Moleton(JogadorBase jogadorDecorado) {
        super(jogadorDecorado);
    }

    @Override
    public int getMoedasCasaSimples() {
        return super.getMoedasCasaSimples() + 2; // Jogador com moletom ganha +2 moedas
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " com Moleton";
    }
}
