public class JogadorFactory {
    
    public static Jogador criarJogador(String tipo, String cor, int numeroCasa, int pulaProximaRodada, int numeroDeJogadas, int pontuacaoEmModedas , boolean boné , boolean moleton , boolean óculosEscuros) {
        switch (tipo.toLowerCase()) {
            case "azarado":
                return new JogadorAzarado(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas,pontuacaoEmModedas,boné,moleton,óculosEscuros);
            case "normal":
                return new JogadorNormal(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas,pontuacaoEmModedas,boné,moleton,óculosEscuros);
            case "sortudo":
                return new JogadorSortudo(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas,pontuacaoEmModedas,boné,moleton,óculosEscuros);
            default:
                throw new IllegalArgumentException("Tipo de jogador desconhecido: " + tipo);
        }
    }
}
