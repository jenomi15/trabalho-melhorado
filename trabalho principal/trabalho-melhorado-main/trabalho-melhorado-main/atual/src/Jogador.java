public abstract class Jogador implements Comparable<Jogador>, JogadorBase {
    private String nome;
    protected String cor;
    protected int tipo;
    protected int numeroCasa;
    protected int pulaProximaRodada;
    private int soma;
    private boolean iguais;
    protected int numeroDeJogadas;
    protected int pontuacaoEmMoedas;
    private boolean boné;
    private boolean moleton;
    private boolean óculosEscuros;
    private boolean DeveJogarNovamente;

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

    public void adicionarMoeda(int quantidade) {
        this.pontuacaoEmMoedas += quantidade;
    }

    public int getPontuacaoEmMoedas() {
        return pontuacaoEmMoedas;
    }

    public int getNumeroDeJogadas() {
        return numeroDeJogadas;
    }

    public void setNumeroDeJogadas(int numeroDeJogadas) {
        this.numeroDeJogadas = numeroDeJogadas;
    }

    public Jogador(String cor, int numeroCasa, int pulaProximaRodada, int numeroDeJogadas, int pontuacaoEmMoedas, boolean bone, boolean moleton, boolean oculosEscuros) {
        this.pulaProximaRodada = pulaProximaRodada;
        this.cor = cor;
        this.numeroCasa = numeroCasa;
        this.numeroDeJogadas = numeroDeJogadas;
        this.pontuacaoEmMoedas = pontuacaoEmMoedas;
        this.boné = bone;
        this.moleton = moleton;
        this.óculosEscuros = oculosEscuros;
        this.DeveJogarNovamente = false;
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

    public int pulaRodada() {
        return this.pulaProximaRodada;
    }

    public void reiniciarPulo(Jogador jogador) {
        this.pulaProximaRodada = 0;
    }

    public void pularProximaRodada() {
        this.pulaProximaRodada += 1;
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

    public void adicionarMoedas(Jogador jogador, int moedasCasaSimples) {
        jogador.setPontuacaoEmMoedas(jogador.getPontuacaoEmMoedas() + moedasCasaSimples);
    }

    public void setPontuacaoEmMoedas(int pontuacaoEmMoedas) {
        this.pontuacaoEmMoedas = pontuacaoEmMoedas;
    }

    public int getPulaProximaRodada() {
        return pulaProximaRodada;
    }

    public void setPulaProximaRodada(int pulaProximaRodada) {
        this.pulaProximaRodada = pulaProximaRodada;
    }

    public boolean isDeveJogarNovamente() {
        return DeveJogarNovamente;
    }

    public void setDeveJogarNovamente(boolean DeveJogarNovamente) {
        this.DeveJogarNovamente = DeveJogarNovamente;
    }

    public void removerMoeda(int quantidade) {
        // Garante que o jogador nunca tenha moedas negativas
        if (this.pontuacaoEmMoedas >= quantidade) {
            this.pontuacaoEmMoedas -= quantidade;
        } else {
            System.out.println("O jogador não tem moedas suficientes! Moedas disponíveis: " + this.pontuacaoEmMoedas);
            this.pontuacaoEmMoedas = 0; // Define a pontuação como zero se não houver moedas suficientes
        }
    }

    @Override
    public String getDescricao() {
        String descricao = "Jogador: cor = " + cor + ", número da casa = " + numeroCasa + " PONTUAÇÃO EM MODEAS = " + pontuacaoEmMoedas ;
        if (temBoné()) descricao += ", com boné";
        if (temMoleton()) descricao += ", com moletom";
        if (temOculosEscuros()) descricao += ", com óculos escuros";
        return descricao;
    }


    public String getTipoDescricao() {
        switch (this.tipo) {
            case 1:
                return "Azarado";
            case 2:
                return "Normal";
            case 3:
                return "Sortudo";
            default:
                return "Desconhecido";
        }
    }
















































    public int getMoedasCasaSimples() {
        int moedas = 1; // Base para Casa Simples
        if (temOculosEscuros()) {
            moedas += 3;
        } 
        if (temMoleton()) {
            moedas += 2;
        }
        if (temBoné()) {
            moedas += 1;
        }
        return moedas;
    }

    
}