import java.util.ArrayList;
import java.util.Scanner;

public class CasaPrisao extends Casa {

    private static final int TAXA = 2;
    private static final int RODADAS_PRESO = 2;

    public CasaPrisao(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        if (jogador.getPulaProximaRodada() == 0) {
            // Jogador acabou de cair na Casa Prisao, fica preso por duas rodadas
            jogador.setPulaProximaRodada(RODADAS_PRESO);
            System.out.println("O jogador " + jogador.getCor() + " foi preso e não jogará por " + RODADAS_PRESO + " rodadas.");
        } else if (jogador.getPulaProximaRodada() == 1) {
            // No turno seguinte, o jogador pode escolher pagar para sair
            Scanner scanner = new Scanner(System.in);
            System.out.println("Você está preso! Deseja pagar uma taxa de " + TAXA + " moedas para sair da prisão agora? (1 - Sim, 2 - Não)");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                if (jogador.getPontuacaoEmMoedas() >= TAXA) {
                    jogador.removerMoeda(TAXA);
                    jogador.setPulaProximaRodada(0); // Libera o jogador
                    System.out.println("O jogador " + jogador.getCor() + " pagou a taxa e saiu da prisão.");
                } else {
                    System.out.println("O jogador " + jogador.getCor() + " não tem moedas suficientes para pagar a taxa.");
                    jogador.setPulaProximaRodada(jogador.getPulaProximaRodada() - 1); // Permanece preso
                }
            } else {
                // O jogador escolheu não pagar, então continua preso
                jogador.setPulaProximaRodada(jogador.getPulaProximaRodada() - 1);
                System.out.println("O jogador " + jogador.getCor() + " escolheu não pagar. Ainda está preso por " + jogador.getPulaProximaRodada() + " rodadas.");
            }
        } else {
            // Jogador continua preso, reduz uma rodada
            jogador.setPulaProximaRodada(jogador.getPulaProximaRodada() - 1);
            System.out.println("O jogador " + jogador.getCor() + " ainda está preso por " + jogador.getPulaProximaRodada() + " rodadas.");
        }
    }

    @Override
    public String toString() {
        return "Casa do tipo Prisão " + getNumero() + " -> Cores: " + cores;
    }
}
