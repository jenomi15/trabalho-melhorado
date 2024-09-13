public class CasaFactory {

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
            case "reversa":
                return new CasaReversa(numero);
            case "sorte":
                return new CasaSorte(numero);
            case "troca":
                return new CasaTroca(numero);
            default:
                throw new IllegalArgumentException("Tipo de casa desconhecido: " + tipo);
        }
    }
}
