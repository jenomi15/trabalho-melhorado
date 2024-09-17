import java.util.List;

public class CasaFactory {

    // Método para criar uma casa sem parâmetros adicionais
    public static Casa criarCasa(String tipo, int numero) {
        switch (tipo.toLowerCase()) {
            case "prisao":
                return new CasaPrisao(numero);
            case "simples":
                return new CasaSimples(numero);
            case "surpresa":
                return new CasaSurpresa(numero); // Chama o construtor sem parâmetros adicionais
            case "azar":
                return new CasaAzar(numero);
            case "jogadenovo":
                return new CasaJogaDeNovo(numero);
            case "troca":
                return new CasaTroca2(numero); // Construtor padrão
            default:
                throw new IllegalArgumentException("Tipo de casa desconhecido: " + tipo);
        }
    }

    // Método para criar uma casa que requer parâmetros adicionais
    public static Casa criarCasa(String tipo, int numero, List<Jogador> jogadores, List<Casa> tabuleiroJogado) {
        switch (tipo.toLowerCase()) {
            case "sorte":
                return new CasaSorte(numero, jogadores, tabuleiroJogado); // Passa jogadores e tabuleiro
            case "reversa":
                return new CasaReversa(numero, jogadores, tabuleiroJogado); // Passa jogadores e tabuleiro
            case "surpresa":
                return new CasaSurpresa(numero, jogadores, tabuleiroJogado); // Passa jogadores e tabuleiro
            default:
                throw new IllegalArgumentException("Tipo de casa desconhecido para parâmetros adicionais: " + tipo);
        }
    }
}
