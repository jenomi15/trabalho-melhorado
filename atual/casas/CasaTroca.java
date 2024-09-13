import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CasaTroca extends Casa {

    public CasaTroca(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        // Scanner pode ser melhor gerenciado fora deste método ou como uma variável de instância
        Scanner scanner = new Scanner(System.in);

        System.out.println("Você caiu na Casa Troca! Escolha um item para trocar seus pontos:");
        System.out.println("1. Boné (Custa 1 ponto)");
        System.out.println("2. Moleton (Custa 3 pontos e requer Boné)");
        System.out.println("3. Óculos Escuros (Custa 6 pontos e requer Boné e Moleton)");
        System.out.println("Digite o número da sua escolha:");

        int escolha = scanner.nextInt();
        switch (escolha) {
            case 1:
                trocarPorBone(jogador);
                break;
            case 2:
                trocarPorMoleton(jogador);
                break;
            case 3:
                trocarPorOculosEscuros(jogador);
                break;
            default:
                System.out.println("Escolha inválida.");
                break;
        }
        // Fechar o Scanner após o uso para evitar vazamentos de recursos
        scanner.close();
    }

    private void trocarPorBone(Jogador jogador) {
        if (jogador.getPontuacaoEmMoedas() >= 1) {
            jogador.removerMoeda(1);
            jogador.setBoné(true);
            System.out.println("Você trocou 1 ponto por um Boné. Agora, você ganhará 2 moedas em casas simples.");
        } else {
            System.out.println("Você não tem pontos suficientes para trocar por um Boné.");
        }
    }

    private void trocarPorMoleton(Jogador jogador) {
        if (jogador.temBoné() && jogador.getPontuacaoEmMoedas() >= 3) {
            jogador.removerMoeda(3);
            jogador.setMoleton(true);
            System.out.println("Você trocou 3 pontos por um Moleton. Agora, você ganhará 4 moedas em casas simples.");
        } else if (!jogador.temBoné()) {
            System.out.println("Você precisa ter um Boné para trocar por um Moleton.");
        } else {
            System.out.println("Você não tem pontos suficientes para trocar por um Moleton.");
        }
    }

    private void trocarPorOculosEscuros(Jogador jogador) {
        if (jogador.temBoné() && jogador.temMoleton() && jogador.getPontuacaoEmMoedas() >= 6) {
            jogador.removerMoeda(6);
            jogador.setOculosEscuros(true);
            System.out.println("Você trocou 6 pontos por Óculos Escuros. Agora, você ganhará 7 moedas em casas simples.");
        } else if (!jogador.temBoné() || !jogador.temMoleton()) {
            System.out.println("Você precisa ter um Boné e um Moleton para trocar por Óculos Escuros.");
        } else {
            System.out.println("Você não tem pontos suficientes para trocar por Óculos Escuros.");
        }
    }
}
