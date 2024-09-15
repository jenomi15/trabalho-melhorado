import java.util.ArrayList;
import java.util.Scanner;

public class CasaTroca2 extends Casa {

   

    public CasaTroca2(int numero) {
        super(numero);
        this.cores = new ArrayList<>();
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        JogadorBase jogadorDecorado = jogador; // Inicialmente, é o jogador normal

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo à Casa de Troca!");
        System.out.println("Escolha um acessório: 1 - Boné, 2 - Moletom, 3 - Óculos Escuros");
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                jogadorDecorado = new Bone(jogadorDecorado);
                break;
            case 2:
                jogadorDecorado = new Moleton(new Bone(jogadorDecorado)); // Precisa do Boné
                break;
            case 3:
                jogadorDecorado = new OculosEscuros(new Moleton(new Bone(jogadorDecorado))); // Precisa do Boné e do Moleton
                break;
            default:
                System.out.println("Escolha inválida!");
        }

        System.out.println("Novo status do jogador: " + jogadorDecorado.getDescricao());
        jogador.adicionarMoedas(jogador, jogadorDecorado.getMoedasCasaSimples()); // Atualiza as moedas do jogador
        System.out.println("Moedas ao cair em Casa Simples: " + jogadorDecorado.getMoedasCasaSimples());
    }
}
