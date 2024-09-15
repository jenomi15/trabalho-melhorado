
public class JogadorAzarado extends Jogador {
      
      private String Azarado; 
    public JogadorAzarado(String cor, int numeroCasa , int pulaProximaRodada , int numeroDeJogadas , int pontuacaoEmModedas ) {
        super(cor, numeroCasa,pulaProximaRodada);
        this.pulaProximaRodada = pulaProximaRodada;
        this.numeroDeJogadas = numeroDeJogadas;
        this.pontuacaoEmMoedas = pontuacaoEmMoedas ;
        
        setTipo(1);
    }

    

    @Override
    public ResultadoDados rolarDados() {
        int soma, dado1, dado2;
        do {
            dado1 = (int) (Math.random() * 6) + 1;
            dado2 = (int) (Math.random() * 6) + 1;
            soma = dado1 + dado2;
        } while (soma > 6); // Garante que a soma nunca seja maior que 6 , como o jgador azarado quer

        System.out.println("O dado 1 rolou: " + dado1);
        System.out.println("O dado 2 rolou: " + dado2);
        System.out.println("a soma dos dados  : "+ soma );
        boolean iguais = (dado1 == dado2);
        return new ResultadoDados(soma, iguais);
    }

    @Override
    public String toString() {
        return "Jogador Azarado: cor = " + getCor() + ",  o numero da Casa = " + getNumeroCasa();
    }
    
    

}

