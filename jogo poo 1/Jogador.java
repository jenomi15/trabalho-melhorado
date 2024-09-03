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
