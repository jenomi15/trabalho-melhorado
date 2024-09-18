import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class CasaSurpresa extends Casa {

    private String[] tiposDeJogador = {"Azarado", "Normal", "Sortudo"};
    private List<Jogador> jogadores;
    private List<Casa> tabuleiroJogado;

    public CasaSurpresa(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    public CasaSurpresa(int numero, List<Jogador> jogadores, List<Casa> tabuleiroJogado) {
        super(numero);
        this.cores = new ArrayList<>();
        this.jogadores = jogadores;
        this.tabuleiroJogado = tabuleiroJogado;
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        tirarCartaAleatoria(jogador);
    }

    private void tirarCartaAleatoria(Jogador jogador) {
        Random rand = new Random();
        int tipoAleatorio;

        // Gera um tipo aleatório diferente do tipo atual do jogador
        do {
            tipoAleatorio = rand.nextInt(3) + 1; // Gera um número entre 1 e 3
        } while (jogador.getTipo() == tipoAleatorio);

        // Altera o tipo do jogador e imprime a mudança
        TrocaJogador(jogador, tipoAleatorio);
        System.out.println("O jogador " + jogador.getCor() + " agora é do tipo " + tiposDeJogador[tipoAleatorio - 1]);
    }

    private void TrocaJogador(Jogador jogador, int tipoJogador) {
        int numeroCasaAtual = jogador.getNumeroCasa();
        int pulaProximaRodada = jogador.pulaRodada(); 
        int pontuacaoEmMoedas = jogador.getPontuacaoEmMoedas(); 
        int numeroDeJogadas = jogador.getNumeroDeJogadas(); 
        boolean boné = jogador.temBoné();
        boolean moleton = jogador.temMoleton();
        boolean óculosEscuros = jogador.temOculosEscuros();
        Jogador novoJogador;

        switch (tipoJogador) {
            case 1:
                novoJogador = new JogadorAzarado(jogador.getCor(), numeroCasaAtual, pulaProximaRodada, numeroDeJogadas,jogador.getPontuacaoEmMoedas(),boné,moleton,óculosEscuros);
                break;
            case 2:
                novoJogador = new JogadorNormal(jogador.getCor(), numeroCasaAtual, pulaProximaRodada, numeroDeJogadas, jogador.getPontuacaoEmMoedas() ,boné,moleton,óculosEscuros);
                break;
            case 3:
                novoJogador = new JogadorSortudo(jogador.getCor(), numeroCasaAtual, pulaProximaRodada, numeroDeJogadas, jogador.getPontuacaoEmMoedas(),boné,moleton,óculosEscuros);
                break;
            default:
                System.out.println("Tipo de jogador inválido");
                return;
        }

        int index = jogadores.indexOf(jogador);
        if (index != -1) {
            jogadores.set(index, novoJogador);
        }
    }

    public String toString() {
        return "Casa do tipo Surpresa " +  getNumero()   + " -> Cores: " + cores;
    }
}
