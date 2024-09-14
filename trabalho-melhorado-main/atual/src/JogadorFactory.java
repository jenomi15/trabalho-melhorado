public class JogadorFactory {
    
    public static Jogador criarJogador(String tipo, String cor, int numeroCasa, int pulaProximaRodada, int numeroDeJogadas, int pontuacaoEmModedas) {
        switch (tipo.toLowerCase()) {
            case "azarado":
                return new JogadorAzarado(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas,pontuacaoEmModedas);
            case "normal":
                return new JogadorNormal(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas,pontuacaoEmModedas);
            case "sortudo":
                return new JogadorSortudo(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas,pontuacaoEmModedas);
            default:
                throw new IllegalArgumentException("Tipo de jogador desconhecido: " + tipo);
        }
    }
}
