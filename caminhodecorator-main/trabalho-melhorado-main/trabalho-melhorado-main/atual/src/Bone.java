public class Bone extends JogadorDecorator {
    public Bone(JogadorBase jogadorDecorado) {
        super(jogadorDecorado);
    }

    @Override
    public int getMoedasCasaSimples() {
        return jogadorDecorado.getMoedasCasaSimples() + 1;
    }

    @Override
    public String getDescricao() {
        return jogadorDecorado.getDescricao() + ", com boné";
    }
}
