public class JogadorFactory {
    
    public static Jogador criarJogador(String tipo, String cor, int numeroCasa, boolean pulaProximaRodada, int numeroDeJogadas) {
        switch (tipo.toLowerCase()) {
            case "azarado":
                return new JogadorAzarado(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas);
            case "normal":
                return new JogadorNormal(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas);
            case "sortudo":
                return new JogadorSortudo(cor, numeroCasa, pulaProximaRodada, numeroDeJogadas);
            default:
                throw new IllegalArgumentException("Tipo de jogador desconhecido: " + tipo);
        }
    }
}
