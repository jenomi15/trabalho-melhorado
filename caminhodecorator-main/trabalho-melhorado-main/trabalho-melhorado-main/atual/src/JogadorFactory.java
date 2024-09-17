
public class JogadorFactory {

    public static Jogador criarJogador(String tipo, String cor, int numeroCasa, int pulaProximaRodada, int numeroDeJogadas, int pontuacaoEmMoedas, boolean boné, boolean moleton, boolean oculosEscuros) {
        switch (tipo.toLowerCase()) {
            case "azarado":
                return new JogadorAzarado(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas, pontuacaoEmMoedas, boné, moleton, oculosEscuros);
            case "normal":
                return new JogadorNormal(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas, pontuacaoEmMoedas, boné, moleton, oculosEscuros);
            case "sortudo":
                return new JogadorSortudo(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas, pontuacaoEmMoedas, boné, moleton, oculosEscuros);
            default:
                throw new IllegalArgumentException("Tipo de jogador desconhecido: " + tipo);
        }
    }
}
