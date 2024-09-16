import java.util.ArrayList;
import java.util.Random;

public class CasaSurpresa extends Casa {

    private String[] tiposDeJogador = {"Azarado", "Normal", "Sortudo"};

    public CasaSurpresa(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        tirarCartaAleatoria(jogador);
    }
     
    private void tirarCartaAleatoria(Jogador jogador) {
        Random rand = new Random();
        int tipoAleatorio = rand.nextInt(3) + 1; // Gera um número entre 1 e 3

        // Altere o tipo do jogador baseado na carta aleatória
        TrocaJogador(jogador, tipoAleatorio);

        System.out.println("O jogador " + jogador.getCor() + " agora é do tipo " + tiposDeJogador[tipoAleatorio - 1]);
    }

    private void TrocaJogador(Jogador jogador, int tipoJogador) {
        int numeroCasaAtual = jogador.getNumeroCasa();
        int pulaProximaRodada = jogador.pulaRodada(); // Assume que é um booleano
        int pontuacaoEmMoedas = jogador.getPontuacaoEmMoedas(); // Assume que é um int
        int numeroDeJogadas = jogador.getNumeroDeJogadas(); // Supondo que existe esse método em Jogador
        
        Jogador novoJogador;
    
        // Cria o novo jogador no tipo selecionado
        switch (tipoJogador) {
            case 1:
                novoJogador = new JogadorAzarado(jogador.getCor(), numeroCasaAtual, pulaProximaRodada, numeroDeJogadas, pontuacaoEmMoedas);
                break;
            case 2:
                novoJogador = new JogadorNormal(jogador.getCor(), numeroCasaAtual, pulaProximaRodada, numeroDeJogadas, pontuacaoEmMoedas);
                break;
            case 3:
                novoJogador = new JogadorSortudo(jogador.getCor(), numeroCasaAtual, pulaProximaRodada, numeroDeJogadas, pontuacaoEmMoedas);
                break;
            default:
                System.out.println("Tipo de jogador inválido");
                return;
        }
    
        // Substitui o jogador antigo pelo novo tipo
        // A lista de jogadores deve ser acessível aqui.
        // Se a lista não estiver na classe CasaSurpresa, você precisa garantir que ela esteja acessível.
    }
    public String toString() {
        return "Casa do tipo Surpresa " +  getNumero()   + " -> Cores: " + cores;
        
        // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
    }
}
