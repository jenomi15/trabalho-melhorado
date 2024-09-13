public class CasaPrisao extends Casa {

    private static final int TAXA = 2;
    private int rodadasPreso = 2;

    public CasaPrisao(int numero) {
        super(numero);
    }

    @Override
    public void aplicarRegra(Jogador jogador) {
        jogador.pularProximaRodada(); 
        System.out.println("Casa Prisão: O jogador " + jogador.getCor() + " está preso e não jogará na próxima rodada.");

        // Simula a possibilidade de pagar taxa
        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja pagar uma taxa de " + TAXA + " moedas para sair da prisão imediatamente? (1 - Sim, 2 - Não)");
        int escolha = scanner.nextInt();

        if (escolha == 1) {
            if (jogador.getPontuacaoEmMoedas() >= TAXA) { // criar getPontuacaoEmMoedas
                jogador.removerMoeda(TAXA); // criar método removerMoeda
                jogador.setNumeroDeRodadasPreso(0); // criar método para resetar rodadas de prisão
                System.out.println("O jogador " + jogador.getCor() + " pagou a taxa e saiu da prisão.");
            } else {
                System.out.println("O jogador " + jogador.getCor() + " não tem moedas suficientes para pagar a taxa.");
            }
        } else {
            jogador.setNumeroDeRodadasPreso(rodadasPreso); // Define as rodadas restantes
        }
    }
}
//criar atributo PontuçãoEmMoedas na classe jogador
