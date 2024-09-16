import java.util.ArrayList;
import java.util.List;


public abstract class Casa {
    private String cor;
    private int numero;
    protected List<String> cores;

    public Casa(int numero  ) {
        this.numero = numero;
        this.cores = new ArrayList<>();
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<String> getCores() {
        return cores;
    }

    public void setCores(List<String> cores) {
        this.cores = cores;
    }

    public void adicionarCor(String cor) {
        cores.add(cor);
    }
   

    public void removerCor(String cor) {
        cores.remove(cor);
    }
    public boolean temCor() {
        return cor != null && !cor.isEmpty();
    }
    public void aplicarRegra(Jogador jogador ){
       System.out.println("regra aplicada");
    }

  
   // @Override
   // public String toString() {
      // StringBuilder sb = new StringBuilder();
       // sb.append("Casa ").append(numero) .append(":\n");
       // sb.append("+--------------------+\n");
       //if (cores.isEmpty()) {
          // sb.append("|       Vazia       |\n");
      // } else {
           // sb.append("| Cores:            |\n");
           //for (String cor : cores) {
              //  sb.append("| ").append(String.format("%-18s", cor)).append("|\n");
           // }
    //  }
      // sb.append("+--------------------+");
       // return sb.toString();
   // }
//}  
 //ACONSELHO PARA A AVALIACAO DO TRABALHO USAR  O DE BAIXO


@Override
public String toString() {
    return "Casa " +  numero   + " -> Cores: " + cores;
    
    // se quiser imprimir da casa 1 a 40 eh so colocar (numero + 1)
}
}
