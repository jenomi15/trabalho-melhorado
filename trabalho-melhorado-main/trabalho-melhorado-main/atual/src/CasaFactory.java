import java.util.List;

public class CasaFactory {

    // Método para criar uma casa que não requer parâmetros adicionais
    public static Casa criarCasa(String tipo, int numero) {
        switch (tipo.toLowerCase()) {
            case "prisao":
                return new CasaPrisao(numero);
            case "simples":
                return new CasaSimples(numero);
            case "surpresa":
                return new CasaSurpresa(numero);
            case "azar":
                return new CasaAzar(numero);
            case "jogadenovo":
                return new CasaJogaDeNovo(numero);
            case "sorte":
                return new CasaSorte(numero, null, null); // Construtor com dois parâmetros
            case "reversa":
                return new CasaReversa(numero, null, null); // Construtor com três parâmetros
            case "troca":
                return new CasaTroca2(numero); // Construtor padrão
            default:
                throw new IllegalArgumentException("Tipo de casa desconhecido: " + tipo);
        }
    }

    // Método para criar casas que requerem parâmetros adicionais
    public static Casa criarCasa(String tipo, int numero, List<Jogador> jogadores, List<Casa> tabuleiroJogado) {
        switch (tipo.toLowerCase()) {
            case "sorte":
                return new CasaSorte(numero, jogadores, null); // Construtor com lista de jogadores
            case "reversa":
                return new CasaReversa(numero, jogadores, tabuleiroJogado); // Construtor com lista de jogadores e tabuleiro
            default:
                throw new IllegalArgumentException("Tipo de casa desconhecido para parâmetros adicionais: " + tipo);
        }
    }
}

