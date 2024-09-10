public class Bone extends JogadorDecorator {
    public Bone(JogadorBase jogadorDecorado) {
        super(jogadorDecorado);
    }

    @Override
    public int getMoedasCasaSimples() {
        return super.getMoedasCasaSimples() + 1; // Jogador com boné ganha +1 moeda
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " com Boné";
    }
}
