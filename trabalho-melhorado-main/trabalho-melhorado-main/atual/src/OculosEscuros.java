public class OculosEscuros extends JogadorDecorator {
    public OculosEscuros(JogadorBase jogadorDecorado) {
        super(jogadorDecorado);
    }

    @Override
    public int getMoedasCasaSimples() {
        return super.getMoedasCasaSimples() + 3; // Jogador com óculos escuros ganha +3 moedas
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " com Óculos Escuros";
    }
}
