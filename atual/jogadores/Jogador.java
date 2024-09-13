public   class Jogador  implements Comparable<Jogador> {
    private String nome;
    private String cor;
    private int tipo;
    private int numeroCasa;
    protected boolean pulaProximaRodada;
    private int soma;
    private boolean iguais;
    protected int numeroDeJogadas;
    protected int pontuacaoEmModedas;
    private boolean boné;
    private boolean moleton;
    private boolean óculosEscuros;

    
    public void setBoné(boolean boné) {
        this.boné = boné;
    }

    public boolean temBoné() {
        return boné;
    }

    public void setMoleton(boolean moleton) {
        this.moleton = moleton;
    }

    public boolean temMoleton() {
        return moleton;
    }

    public void setOculosEscuros(boolean oculosEscuros) {
        this.óculosEscuros = oculosEscuros;
    }

    public boolean temOculosEscuros() {
        return óculosEscuros;
    }

    public void removerMoeda(int quantidade) {
        this.pontuacaoEmMoedas -= quantidade;
    }

    public void adicionarMoeda(int quantidade) {
        this.pontuacaoEmMoedas += quantidade;
    }

    public int getPontuacaoEmMoedas() {
        return pontuacaoEmMoedas;
    }
    
    // Método para atualizar a pontuação com base no item equipado
    public int calcularMoedasCasaSimples() {
        int moedas = 1; // Base para Casa Simples
        if (temOculosEscuros()) {
            moedas += 3;
        } else if (temMoleton()) {
            moedas += 2;
        } else if (temBoné()) {
            moedas += 1;
        }
        return moedas;
    }

    
    
    public int getNumeroDeJogadas() {
        return numeroDeJogadas;
    }

    public void setNumeroDeJogadas(int numeroDeJogadas) {
        this.numeroDeJogadas = numeroDeJogadas;
    }

    public Jogador(String cor, int numeroCasa , boolean pulaProximaRodada) {
        this.pulaProximaRodada = false;
        this.cor = cor;
        this.numeroCasa = numeroCasa;
        this.numeroDeJogadas = 0;
        this.pontuacaoEmModedas = 0;
    }
    
    public int getSoma() {
        return soma;
    }

    public boolean isIguais() {
        return iguais;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getTipo() {
        return this.tipo;
    }

    public int getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(int numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public boolean pulaRodada() {
        return this.pulaProximaRodada;
    }

    public void reiniciarPulo() {
        this.pulaProximaRodada = false;
    }

    public void pularProximaRodada() {
        this.pulaProximaRodada = true;
    }

    public ResultadoDados rolarDados() {
        int dado1 = (int) (Math.random() * 6) + 1;
        int dado2 = (int) (Math.random() * 6) + 1;
        int soma = dado1 + dado2;
        boolean iguais = (dado1 == dado2);
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
        return "Jogador: cor=" + cor + ", numeroCasa=" + numeroCasa;
    }

    @Override
    public int compareTo(Jogador outroJogador) {
        return Integer.compare(outroJogador.getNumeroCasa(), this.numeroCasa);
    }
}
