import java.util.Scanner;

public class CasaTroca2 extends Casa {

    public CasaTroca2(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        // Supondo que 'jogador' seja um objeto da classe Jogador
        JogadorBase jogadorDecorado =  (JogadorBase) jogador; // Convertendo para JogadorBase

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Casa de Troca!");
        System.out.println("Escolha um acessório: 1 - Boné (1 moeda), 2 - Moletom (3 moedas), 3 - Óculos Escuros (6 moedas)");
        int escolha = scanner.nextInt();

        int preco = 0;

        switch (escolha) {
            case 1:
                preco = 1;
                jogadorDecorado = new Bone(jogadorDecorado);
                break;
            case 2:
                preco = 3;
                jogadorDecorado = new Moleton(new Bone(jogadorDecorado)); // Precisa do Boné
                break;
            case 3:
                preco = 6;
                jogadorDecorado = new OculosEscuros(new Moleton(new Bone(jogadorDecorado))); // Precisa do Boné e do Moleton
                break;
            default:
                System.out.println("Escolha inválida!");
                return; // Sai do método se a escolha for inválida
        }

        if (jogador.getPontuacaoEmMoedas() >= preco) {
            jogador.removerMoeda(preco); 
            System.out.println("Você comprou o acessório por " + preco + " moedas.");
            System.out.println("Novo status do jogador: " + jogadorDecorado.getDescricao());
        } else {
            System.out.println("Você não tem moedas suficientes para comprar este acessório.");
        }

        jogador.adicionarMoedas(jogador, jogadorDecorado.getMoedasCasaSimples());
        System.out.println("Moedas ao cair em Casa Simples: " + jogadorDecorado.getMoedasCasaSimples());
    }

    @Override
    public String toString() {
        return "Casa do tipo troca " +  (getNumero()-1)  + " -> Cores: " + cores;
    }
}
