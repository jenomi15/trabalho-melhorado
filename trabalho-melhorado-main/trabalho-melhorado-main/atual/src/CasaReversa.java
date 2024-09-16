import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CasaReversa extends Casa {

    private List<Jogador> jogadores;
    private List<Casa> tabuleiroJogado;

    // Construtor padrão
    public CasaReversa(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
        // Lista de jogadores e tabuleiro não inicializados
        this.jogadores = null;
        this.tabuleiroJogado = null;
    }

    // Construtor com parâmetros adicionais
    public CasaReversa(int numero, List<Jogador> jogadores, List<Casa> tabuleiroJogado) {
        super(numero);
        this.jogadores = jogadores;
        this.tabuleiroJogado = tabuleiroJogado;
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        if (jogadores != null && tabuleiroJogado != null) {
            trocarDeLugarComUltimo(jogador);
        } else {
            System.out.println("CasaReversa não está completamente configurada.");
        }
    }

    private void trocarDeLugarComUltimo(Jogador jogador) {
        // Encontrar a posição mais baixa no tabuleiro
        int menorNumeroCasa = jogadores.stream()
            .mapToInt(Jogador::getNumeroCasa)
            .min()
            .orElse(-1);

        // Encontrar todos os jogadores que estão na posição mais baixa
        List<Jogador> ultimosJogadores = jogadores.stream()
            .filter(j -> j.getNumeroCasa() == menorNumeroCasa)
            .collect(Collectors.toList());

        Jogador ultimoJogador;

        if (ultimosJogadores.size() > 1) {
            ultimoJogador = escolherAleatoriamente(ultimosJogadores);
        } else {
            ultimoJogador = ultimosJogadores.get(0);
        }

        if (jogador != ultimoJogador) {
            int posicaoAtual = jogador.getNumeroCasa();
            jogador.setNumeroCasa(ultimoJogador.getNumeroCasa());
            ultimoJogador.setNumeroCasa(posicaoAtual);

            // Atualizar as cores no tabuleiro
            atualizarTabuleiro(jogador, ultimoJogador);

            System.out.println("O jogador " + jogador.getCor() + " trocou de posição com o jogador " + ultimoJogador.getCor());
        } else {
            System.out.println("O jogador já está na última posição e não pode trocar!");
        }
    }

    private void atualizarTabuleiro(Jogador jogador, Jogador ultimoJogador) {
        // Atualiza as cores das casas no tabuleiro
        Casa casaJogador = tabuleiroJogado.get(jogador.getNumeroCasa());
        Casa casaUltimoJogador = tabuleiroJogado.get(ultimoJogador.getNumeroCasa());

        casaJogador.removerCor(ultimoJogador.getCor());
        casaUltimoJogador.removerCor(jogador.getCor());
        casaJogador.adicionarCor(jogador.getCor());
        casaUltimoJogador.adicionarCor(ultimoJogador.getCor());
    }

    private Jogador escolherAleatoriamente(List<Jogador> jogadores) {
        Random rand = new Random();
        return jogadores.get(rand.nextInt(jogadores.size()));
    }
    public String toString() {
        return "Casa do tipo reversa " +  (getNumero()-1)  + " -> Cores: " + cores;
        
        // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
    }
}