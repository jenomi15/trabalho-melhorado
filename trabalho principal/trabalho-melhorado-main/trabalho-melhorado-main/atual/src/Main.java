import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("BEM VINDO AO JOGO DE TABULEIRO\nDigite quantos jogadores você quer criar: ");
        int j = teclado.nextInt();

        // Garantir que o número de jogadores esteja entre 1 e 6
        while (j < 1 || j > 6) {
            System.out.println("Número de jogadores inválido. Por favor, selecione um número de jogadores entre 1 e 6.");
            j = teclado.nextInt();
        }

        ArrayList<Jogador> jogadores = new ArrayList<>();
        teclado.nextLine();  // Consumir a nova linha após o próximoInt

        // Mapa para rastrear o número de jogadores de cada tipo
        Map<String, Integer> contagemPorTipo = new HashMap<>();
        contagemPorTipo.put("azarado", 0);
        contagemPorTipo.put("normal", 0);
        contagemPorTipo.put("sortudo", 0);

        // Definir o limite de jogadores por tipo
        int limitePorTipo = j / 3;
        if (j % 3 != 0) {
            limitePorTipo++; // Se não for divisível exatamente por 3, aumenta o limite em 1
        }

        for (int i = 0; i < j; i++) {
            System.out.println("Digite a cor do " + (i + 1) + "º jogador: ");
            String cor = teclado.nextLine();

            String tipo = "";
            boolean tipoValido = false;

            // Repetir até que o jogador selecione um tipo válido e que não tenha excedido o limite
            while (!tipoValido) {
                System.out.println("Digite o tipo do jogador (azarado, normal, sortudo): ");
                tipo = teclado.nextLine().toLowerCase();

                // Verifica se o tipo é válido e não excedeu o limite
                if (contagemPorTipo.containsKey(tipo) && contagemPorTipo.get(tipo) < limitePorTipo) {
                    tipoValido = true; // Tipo válido e limite não excedido
                    contagemPorTipo.put(tipo, contagemPorTipo.get(tipo) + 1); // Atualiza a contagem
                } else {
                    System.out.println("Você atingiu o limite de jogadores do tipo " + tipo + ". Escolha outro tipo.");
                }
            }

            // Utiliza a Factory para criar o jogador do tipo fornecido
            Jogador jogador = JogadorFactory.criarJogador(tipo, cor, 0, 0, 0, 0, false, false, false);
            jogadores.add(jogador);
        }

        // Exibir informações dos jogadores criados
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println("Jogador " + (i + 1) + " - Cor: " + jogador.getCor() + " - Tipo: " + jogador.getClass().getSimpleName());
        }

        // Obter a instância única do Tabuleiro usando o Singleton
        Tabuleiro tabuleiro = Tabuleiro.getInstancia(jogadores);
        tabuleiro.criacaoDoTabuleiro();

        // Inicializar as casas do tabuleiro com as cores dos jogadores
        for (int l = 0; l < j; l++) {
            tabuleiro.adicionarCorCasa(0, jogadores.get(l).getCor());
        }

        // Imprimir o estado inicial do tabuleiro
        tabuleiro.imprimirTabuleiro();

        // Selecionar o modo de jogo
        System.out.println("Selecione o modo de jogo: ");
        System.out.println("1 - Modo Normal");
        System.out.println("2 - Modo Debug");
        int opcao = teclado.nextInt();

        // Iniciar o jogo de acordo com a opção selecionada
        if (opcao == 1) {
            System.out.println("Iniciando o jogo no modo normal...");
            tabuleiro.TurnoDoJogo();
        } else if (opcao == 2) {
            System.out.println("Iniciando o jogo no modo debug...");
            tabuleiro.TurnoDoJogoDebug();
        } else {
            System.out.println("Opção inválida. Iniciando no modo normal por padrão...");
            tabuleiro.TurnoDoJogo();
        }

        teclado.close();
    }
}