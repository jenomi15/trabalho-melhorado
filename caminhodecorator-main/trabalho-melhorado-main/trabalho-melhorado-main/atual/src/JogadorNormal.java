
public class JogadorNormal extends Jogador {

    public JogadorNormal(String cor, int numeroCasa, int pulaProximaRodada, int numeroDeJogadas, int pontuacaoEmMoedas, boolean boné, boolean moleton, boolean oculosEscuros) {
        super(cor, pontuacaoEmMoedas, pontuacaoEmMoedas, pontuacaoEmMoedas, pontuacaoEmMoedas, oculosEscuros, oculosEscuros, oculosEscuros);
        this.cor = cor ;
        this.numeroCasa = numeroCasa;
        this.pulaProximaRodada = pulaProximaRodada;
        this.numeroDeJogadas = numeroDeJogadas;
        this.pontuacaoEmMoedas = pontuacaoEmMoedas;

        // Define os itens decorativos usando os métodos apropriados
        setBoné(boné);
        setMoleton(moleton);
        setOculosEscuros(oculosEscuros);

        setTipo(2); // Define o tipo como Normal
    }

    @Override
    public ResultadoDados rolarDados() {
        int dado1 = (int) (Math.random() * 6) + 1; 
        int dado2 = (int) (Math.random() * 6) + 1; 
        int soma = dado1 + dado2;

        // Exibe os resultados dos dados no console
        System.out.println("O dado 1 rolou: " + dado1);
        System.out.println("O dado 2 rolou: " + dado2);
        System.out.println("A soma dos dados: " + soma);

        boolean iguais = (dado1 == dado2); // Verifica se os dados são iguais
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
        return "Jogador Normal: cor = " + getCor() + ", número da casa = " + getNumeroCasa();
    }

    @Override
    public void adicionarMoedas(int quantidade) {
        super.adicionarMoeda(quantidade); // Usa o método da classe pai para adicionar moedas
    }
}


