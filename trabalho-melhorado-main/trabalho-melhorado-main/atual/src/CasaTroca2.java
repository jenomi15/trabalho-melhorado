import java.util.Scanner;

public class CasaTroca2 extends Casa {

    public CasaTroca2(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Casa de Troca!");
        System.out.println("Escolha um acessório: 1 - Boné (1 moeda), 2 - Moletom (3 moedas), 3 - Óculos Escuros (6 moedas)");
        int escolha = scanner.nextInt();

        // Inicialmente não decorado
        JogadorBase jogadorDecorado = jogador;
        
        // Verifica se o jogador já possui os acessórios necessários antes de permitir a compra
        switch (escolha) {
            case 1:
                // Compra de Boné
                if (jogador.temBoné()) {
                    System.out.println("Você já possui um Boné.");
                } else {
                    int precoBoné = 1;
                    jogadorDecorado = new Bone(jogadorDecorado);
                    realizarCompra(jogador, jogadorDecorado, precoBoné);
                }
                break;
            case 2:
                // Compra de Moletom
                if (jogador.temMoleton()) {
                    System.out.println("Você já possui um Moletom.");
                } else if (!jogador.temBoné()) {
                    System.out.println("Você precisa ter um Boné para comprar um Moletom.");
                } else {
                    int precoMoletom = 3;
                    jogadorDecorado = new Moleton(new Bone(jogadorDecorado)); // Precisa do Boné
                    realizarCompra(jogador, jogadorDecorado, precoMoletom);
                }
                break;
            case 3:
                // Compra de Óculos Escuros
                if (jogador.temOculosEscuros()) {
                    System.out.println("Você já possui Óculos Escuros.");
                } else if (!jogador.temBoné() || !jogador.temMoleton()) {
                    System.out.println("Você precisa ter um Boné e um Moletom para comprar Óculos Escuros.");
                } else {
                    int precoOculosEscuros = 6;
                    jogadorDecorado = new OculosEscuros(new Moleton(new Bone(jogadorDecorado))); // Precisa do Boné e do Moletom
                    realizarCompra(jogador, jogadorDecorado, precoOculosEscuros);
                }
                break;
            default:
                System.out.println("Escolha inválida!");
                break;
        }
    }

    private void realizarCompra(Jogador jogador, JogadorBase jogadorDecorado, int preco) {
        if (jogador.getPontuacaoEmMoedas() >= preco) {
            jogador.removerMoeda(preco);
            System.out.println("Você comprou o acessório por " + preco + " moedas.");
            System.out.println("Novo status do jogador: " + jogadorDecorado.getDescricao());
            int moedasCasaSimples = jogadorDecorado.getMoedasCasaSimples(); // Corrigido para obter a quantidade correta
            jogador.adicionarMoedas(jogador, moedasCasaSimples); // Corrigido para usar a quantidade correta    // eesa linha talvez adicione
            System.out.println("Moedas ao cair em Casa Simples: " + moedasCasaSimples);  
        } else {
            System.out.println("Você não tem moedas suficientes para comprar este acessório.");
        }
    }

    @Override
    public String toString() {
        return "Casa do tipo troca " + getNumero()  + " -> Cores: " + cores;
    }
}
