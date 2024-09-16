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
    public String toString() {
        return "Casa do tipo Joga de novo " +  (getNumero()-1)  + " -> Cores: " + cores;
        
        // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
    }
}
