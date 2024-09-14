import java.util.ArrayList;

public class CasaJogaDeNovo extends Casa {
    
    public CasaJogaDeNovo(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }
    
    @Override
    public void aplicarRegra(Jogador jogador) {
        // Em vez de tentar manipular a variável 'a' diretamente, você pode usar uma abordagem diferente
        jogador.setDeveJogarNovamente(true);
    }
}
