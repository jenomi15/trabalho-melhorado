import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;


public class Tabuleiro {
    private ArrayList<Casa> tabuleiroJogado;
    private ArrayList<Jogador> jogadores; 
    private String[] tiposDeJogador = {"Azarado", "Normal", "Sortudo"};
    private static Tabuleiro instanciaUnica;
    private static ArrayList<Jogador> jogadoresInstance;

   
    private Tabuleiro(ArrayList<Jogador> jogadores) { 
        this.tabuleiroJogado = new ArrayList<>();
        this.jogadores = jogadores; 
    }

    
    public static synchronized Tabuleiro getInstancia(ArrayList<Jogador> jogadores) {
        if (instanciaUnica == null) {
            jogadoresInstance = jogadores;
            instanciaUnica = new Tabuleiro(jogadoresInstance);
        }
        return instanciaUnica;
    }

    
    public static Tabuleiro getInstancia() {
        if (instanciaUnica == null) {
            throw new IllegalStateException("A instância não foi criada ainda. Use getInstancia(ArrayList<Jogador> jogadores) para inicializar.");
        }
        return instanciaUnica;
    }
    public List<Casa> getCasas() {
        return tabuleiroJogado;
    }

    public void criacaoDoTabuleiro() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> casasEscolhidas = new ArrayList<>();
    
        
        System.out.println("Digite o total de casas para o tabuleiro:");
        int totalCasas = scanner.nextInt();
        scanner.nextLine(); 
    
        
        tabuleiroJogado = new ArrayList<>(Collections.nCopies(totalCasas, null));
    
        String[] tiposDeCasas = {"surpresa", "sorte", "reversa", "prisao", "jogadenovo", "azar", "troca"};
    
        
        for (String tipo : tiposDeCasas) {
            System.out.println("Quantas casas do tipo " + tipo + " deseja criar?");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); 
    
            for (int i = 0; i < quantidade; i++) {
                System.out.println("Digite a posição para a casa " + (i + 1) + " do tipo " + tipo + ":");
                int numero = scanner.nextInt();
                scanner.nextLine(); 
    
                while (casasEscolhidas.contains(numero) || numero < 0 || numero >= totalCasas) {
                    if (casasEscolhidas.contains(numero)) {
                        System.out.println("Posição já escolhida. Digite uma nova posição para a casa " + (i + 1) + " do tipo " + tipo + ":");
                    } else {
                        System.out.println("Posição inválida. Digite uma posição entre 0 e " + (totalCasas - 1) + ":");
                    }
                    numero = scanner.nextInt();
                    scanner.nextLine(); 
                }
    
                casasEscolhidas.add(numero); 
    
               
                Casa novaCasa;
                if (tipo.equals("sorte") || tipo.equals("reversa")) {
                    novaCasa = CasaFactory.criarCasa(tipo, numero, jogadores, tabuleiroJogado);
                } else {
                    novaCasa = CasaFactory.criarCasa(tipo, numero);
                }
    
              
                tabuleiroJogado.set(numero, novaCasa);
            }
        }
    
        
        for (int i = 0; i < totalCasas; i++) {
            if (tabuleiroJogado.get(i) == null) {
                tabuleiroJogado.set(i, CasaFactory.criarCasa("simples", i));
            }
        }
    
        System.out.println("Tabuleiro criado com sucesso!");
    }
    
    
     
    
    
    

    
    
    public int getNumeroDeCasas() {
        return tabuleiroJogado.size();
    }

    
    public void adicionarCorCasa(int posicao, String cor) {
        if (posicao >= 0 && posicao < tabuleiroJogado.size()) {
            tabuleiroJogado.get(posicao).adicionarCor(cor);
        } else {
            System.out.println("Posição inválida");
        }
    }
    
    public void removerCorCasa(int posicao, String cor) {
        if (posicao >= 0 && posicao < tabuleiroJogado.size()) {
            tabuleiroJogado.get(posicao).removerCor(cor);
        } else {
            System.out.println("Posição inválida");
        }
    }

    public void imprimirTabuleiro() {
        for (Casa casa : tabuleiroJogado) {
            System.out.println(casa);
        }
    }
    public void TurnoDoJogo() {
        Scanner teclado1 = new Scanner(System.in);
        
        while (!jogoTerminou()) {
            for (int a = 0; a < jogadores.size(); a++) {
                Jogador jogadorAtual = jogadores.get(a);
                int posicaoAntiga = jogadorAtual.getNumeroCasa();
    
                // Verifica se o jogador ganhou e encerra o jogo imediatamente
                if (posicaoAntiga >= tabuleiroJogado.size() - 1) {
                    System.out.println("O jogador " + jogadorAtual.getCor() + " venceu o jogo!");
    
                    Collections.sort(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa).reversed());
    
                    System.out.println("Posições dos jogadores:");
                    for (int w = 0; w < jogadores.size(); w++) {
                        Jogador jogador = jogadores.get(w);
                        System.out.println((w + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa());
                    }
                    teclado1.close();
                    return; // Encerra o método e o jogo
                }
    
                // Pular rodada se necessário
                if (jogadorAtual.pulaRodada() > 0) {
                    System.out.println("O jogador " + jogadorAtual.getCor() + " que possui um número de moedas = " + jogadorAtual.getPontuacaoEmMoedas() + " está pulando esta rodada.");
                    jogadorAtual.setPulaProximaRodada(0);
                    continue; 
                }
    
                // Processo de rolar dados e mover jogador
                System.out.println("Turno do jogador N* " + (a + 1) + " (" + jogadorAtual.getCor() + " que possui um número de moedas = (" + jogadorAtual.getPontuacaoEmMoedas() + "), role os dados.");
                ResultadoDados resultado = jogadorAtual.rolarDados();
                int novaPosicao = posicaoAntiga + resultado.getSoma();
                if (novaPosicao >= tabuleiroJogado.size()) {
                    novaPosicao = tabuleiroJogado.size() - 1;
                }
    
                System.out.println("\n Posição antiga do jogador " + jogadorAtual.getCor() + ": " + posicaoAntiga);
                System.out.println("Nova posição calculada para o jogador " + jogadorAtual.getCor() + ": " + novaPosicao);
    
                // Atualiza a posição do jogador no tabuleiro
                removerCorCasa(posicaoAntiga, jogadorAtual.getCor());
                jogadorAtual.setNumeroCasa(novaPosicao);
                adicionarCorCasa(novaPosicao, jogadorAtual.getCor());
    
                // Aplica a regra da casa em que o jogador parou
                tabuleiroJogado.get(novaPosicao).aplicarRegra(jogadorAtual);
    
                // Imprime o estado atual do tabuleiro
                imprimirTabuleiro();
    
                // Verifica se o jogador deve jogar novamente
                if (jogadorAtual.isDeveJogarNovamente()) {
                    removerCorCasa(novaPosicao, jogadorAtual.getCor());
                    a--; // Repetir turno do mesmo jogador
                    continue;
                }
    
                // Verifica se o jogador rolou dois dados iguais
                if (resultado.isIguais()) {
                    System.out.println("O jogador " + jogadorAtual.getCor() + " rolou dois dados iguais e joga novamente.");
                    removerCorCasa(novaPosicao, jogadorAtual.getCor());
                    a--; // Repetir turno do mesmo jogador
                }
    
                // Pergunta se o jogador deseja continuar
                System.out.println("\n Deseja continuar? \n 1 - sim \n 2 - não ");
                Scanner teclado3 = new Scanner(System.in);
                int p = teclado3.nextInt();
                if (p == 2) {
                    teclado3.close();
                    return; // Encerra o método e o jogo
                }
            }
        }
    
        // Se o loop terminar (o que não deveria acontecer com a verificação de vitória), imprime as posições finais
        Collections.sort(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa).reversed());
    
        System.out.println("Posições dos jogadores:");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println((i + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa());
        }
    }
    

 
    
    public void TrocaJogador(Jogador jogador, int tipoJogador, int a) {
        int numeroCasaAtual = jogador.getNumeroCasa();
        int  pulaProximaRodada = jogador.pulaRodada(); 
        int numeroDeJogadas = jogador.getNumeroDeJogadas(); 
        int pontuacaoEmMoedas = jogador.getPontuacaoEmMoedas(); 
        
        Jogador novoJogador;
    
        switch (tipoJogador) {
            case 1:
                novoJogador = new JogadorAzarado(jogador.getCor(), numeroCasaAtual, pulaProximaRodada , numeroDeJogadas, pontuacaoEmMoedas);
                break;
            case 2:
                novoJogador = new JogadorNormal(jogador.getCor(), numeroCasaAtual, pulaProximaRodada , numeroDeJogadas, pontuacaoEmMoedas);
                break;
            case 3:
                novoJogador = new JogadorSortudo(jogador.getCor(), numeroCasaAtual, pulaProximaRodada , numeroDeJogadas, pontuacaoEmMoedas);
                break;
            default:
                System.out.println("Tipo de jogador inválido");
                return;
        }
    
    
        int index = jogadores.indexOf(jogador);
        if (index != -1) {
            jogadores.set(index, novoJogador);
        }
    }
    
    
   private void trocarDeLugarComUltimo(Jogador jogador) {
    List<Jogador> ultimosJogadores = jogadores.stream()
        .filter(j -> j.getNumeroCasa() == Collections.min(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa)).getNumeroCasa())
        .collect(Collectors.toList());

    Jogador ultimoJogador;

    if (ultimosJogadores.size() > 1) {
        ultimoJogador = escolherAleatoriamente(ultimosJogadores);
    } else {
        ultimoJogador = ultimosJogadores.get(0);
    }

    if (jogador != ultimoJogador) {
        int posicaoAtual = jogador.getNumeroCasa();
        jogador.setNumeroCasa(ultimoJogador.getNumeroCasa());
        ultimoJogador.setNumeroCasa(posicaoAtual);

        tabuleiroJogado.get(jogador.getNumeroCasa()).removerCor(ultimoJogador.getCor());
        tabuleiroJogado.get(posicaoAtual).removerCor(jogador.getCor());
        tabuleiroJogado.get(jogador.getNumeroCasa()).adicionarCor(jogador.getCor());
        tabuleiroJogado.get(ultimoJogador.getNumeroCasa()).adicionarCor(ultimoJogador.getCor());

        System.out.println("O jogador " + jogador.getCor() + " trocou de posição com o jogador " + ultimoJogador.getCor());
    } else {
        System.out.println("O jogador já está na última posição e não pode trocar!");
    }
}

private Jogador escolherAleatoriamente(List<Jogador> jogadores) {
    Random random = new Random();
    return jogadores.get(random.nextInt(jogadores.size()));
}



   
    private void escolherCompetidorVoltarInicio(Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha um competidor para voltar ao início:");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println((i + 1) + " - " + jogadores.get(i).getCor());
        }
        int escolha = scanner.nextInt();
        if ( escolha < 1 || escolha > jogadores.size() - 1 ){
           do{
         System.out.println("escolha um jogador válido , por obsequio");
           }while( escolha < 1 || escolha < jogadores.size() - 1);
        }


        tabuleiroJogado.get(jogadores.get(escolha - 1 ).getNumeroCasa()).removerCor(jogadores.get(escolha-1).getCor());
        jogadores.get(escolha - 1).setNumeroCasa(0);
        System.out.println("O jogador " + jogadores.get(escolha - 1).getCor() + " voltou para o início!");
        tabuleiroJogado.get(0).adicionarCor(jogadores.get(escolha-1).getCor()); 
                                                        
    }


    public void verificacaoCasa(int numeroDaCasa , Jogador jogador) {
        if (numeroDaCasa == 10 || numeroDaCasa == 25 || numeroDaCasa == 38) {
            System.out.println("O jogador não joga na próxima rodada.");
            jogador.pularProximaRodada();
        } else if (numeroDaCasa == 13) {
            System.out.println("Casa surpresa! O jogador deve tirar uma carta.");
            tirarCartaAleatoria(jogador);
        } else if (numeroDaCasa == 5 || numeroDaCasa == 15 || numeroDaCasa == 30) {
            if (!(jogador instanceof JogadorAzarado)) {
                System.out.println("Casa da sorte! O jogador anda 3 casas para frente.");
                tabuleiroJogado.get(numeroDaCasa).removerCor(jogador.getCor());
                jogador.setNumeroCasa(jogador.getNumeroCasa() + 3);
                tabuleiroJogado.get(jogador.getNumeroCasa()).adicionarCor(jogador.getCor());
            }
        } else if (numeroDaCasa == 17 || numeroDaCasa == 27) {
            System.out.println("O jogador escolhe um competidor para voltar ao início.");
            escolherCompetidorVoltarInicio(jogador);
        } else if (numeroDaCasa == 20 || numeroDaCasa == 35) {
            System.out.println("Casa mágica! O jogador troca de lugar com o último jogador.");
            trocarDeLugarComUltimo(jogador);
        }
    }
    private boolean verificarDiversidadeTipos() {
        Set<Integer> tipos = new HashSet<>();
        for (Jogador jogador : jogadores) {
            tipos.add(jogador.getTipo());
        }
        
        return tipos.size() > 1;
    }





    private void tirarCartaAleatoria(Jogador jogador) {
        Random rand = new Random();
        int tipoAleatorio = rand.nextInt(3) + 1;
        TrocaJogador(jogador, tipoAleatorio, 1);
        System.out.println("O jogador " + jogador.getCor() + " agora é do tipo " + tiposDeJogador[tipoAleatorio - 1]);
    }

    private boolean jogoTerminou() {
       
        for (Jogador jogador : jogadores) {
            if (jogador.getNumeroCasa() >= tabuleiroJogado.size() - 1 ) {
                System.out.println("O jogador " + jogador.getCor() + " venceu!");
                return true;
            }
        }
        return false;



    }
    public void selecionarTiposDeJogadores() {
        Scanner teclado1 = new Scanner(System.in);
        int quantidadeDeJogadores = jogadores.size();
        boolean tiposDiversos;
    
        do {
            tiposDiversos = true;
            System.out.println("Selecione os tipos de jogadores:");
    
            for (int o = 0; o < quantidadeDeJogadores; o++) {
                System.out.println("Você está selecionando o jogador " + jogadores.get(o).getCor() +
                                   "\nDigite \n1 para ele ser Azarado\n2 para ele ser Normal\n3 para ele ser Sortudo");
                int tipoJogador = teclado1.nextInt();
    
                while (tipoJogador < 1 || tipoJogador > 3) {
                    System.out.println("Você selecionou um tipo inválido, por favor, digite corretamente.");
                    tipoJogador = teclado1.nextInt();
                }
    
                TrocaJogador(jogadores.get(o), tipoJogador, o);
            }
    
           
            if (!verificarDiversidadeTipos()){ 
                tiposDiversos = false;
                System.out.println("Os tipos de jogadores não são diversos o suficiente. " +
                                   "Haverá pelo menos dois tipos diferentes de jogadores.");
            }
    
        } while (!tiposDiversos);
    }
    public void TurnoDoJogoDebug() {
        Scanner teclado1 = new Scanner(System.in);
        //  selecionarTiposDeJogadores();
        
        while (!jogoTerminou()) {
            for (int a = 0; a < jogadores.size(); a++) {
                Jogador jogadorAtual = jogadores.get(a);
                int posicaoAntiga = jogadorAtual.getNumeroCasa();
                
                // Verifica se o jogador ganhou e encerra o jogo imediatamente
                if (posicaoAntiga >= tabuleiroJogado.size() - 1) {
                    System.out.println("O jogador " + jogadorAtual.getCor() + " venceu o jogo!");
                    
                    Collections.sort(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa).reversed());
                    
                    System.out.println("Posições dos jogadores:");
                    for (int w = 0; w < jogadores.size(); w++) {
                        Jogador jogador = jogadores.get(w);
                        System.out.println((w + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa());
                    }
                    teclado1.close();
                    return; // Encerra o método e o jogo
                }
                
                // Pular rodada se necessário
                if (jogadorAtual.pulaRodada() > 0) {
                    System.out.println("O jogador " + jogadorAtual.getCor() + " que possui um número de moedas = " + jogadorAtual.getPontuacaoEmMoedas() + " está pulando esta rodada.");
                    jogadorAtual.setPulaProximaRodada(0);
                    continue; 
                }
                
                // Solicita a posição da casa para a qual o jogador deve ir
                System.out.println("Turno do jogador N* " + (a + 1) + " (" + jogadorAtual.getCor() + " que possui um número de moedas = " + jogadorAtual.getPontuacaoEmMoedas() + ").");
                System.out.println("Digite a posição da casa para a qual o jogador deve ir (0 a " + (tabuleiroJogado.size() - 1) + "):");
                Scanner teclado2 = new Scanner(System.in);
                int novaPosicao = teclado2.nextInt();
                
                // Verifica se a nova posição é válida
                if (novaPosicao < 0 || novaPosicao >= tabuleiroJogado.size()) {
                    System.out.println("Posição inválida! O jogador permanecerá na posição atual.");
                    novaPosicao = posicaoAntiga;
                }
                
                System.out.println("\n Posição antiga do jogador " + jogadorAtual.getCor() + ": " + posicaoAntiga);
                System.out.println("Nova posição calculada para o jogador " + jogadorAtual.getCor() + ": " + novaPosicao);
                
                // Atualiza a posição do jogador no tabuleiro
                removerCorCasa(posicaoAntiga, jogadorAtual.getCor());
                jogadorAtual.setNumeroCasa(novaPosicao);
                adicionarCorCasa(novaPosicao, jogadorAtual.getCor());
                
                // Aplica a regra da casa em que o jogador parou
                tabuleiroJogado.get(novaPosicao).aplicarRegra(jogadorAtual);
                
                // Imprime o estado atual do tabuleiro
                imprimirTabuleiro();
                
                // Verifica se o jogador deve jogar novamente
                if (jogadorAtual.isDeveJogarNovamente()) {
                    removerCorCasa(novaPosicao, jogadorAtual.getCor());
                    a--; // Repetir turno do mesmo jogador
                    continue;
                }
                
                // Pergunta se o jogador deseja continuar
                System.out.println("\n Deseja continuar? \n 1 - sim \n 2 - não ");
                int p = teclado2.nextInt();
                if (p == 2) {
                    teclado2.close();
                    return; // Encerra o método e o jogo
                }
            }
        }
        
        // Se o loop terminar (o que não deveria acontecer com a verificação de vitória), imprime as posições finais
        Collections.sort(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa).reversed());
        
        System.out.println("Posições dos jogadores:");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println((i + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa());
        }
    }
    
    
    
    


    public List<Jogador> getJogadores() {
        return jogadores;
    }
    
    
    





    public static ArrayList<Jogador> criarJogadores1(int quantidadeJogadores) {
        Scanner teclado = new Scanner(System.in);

       
        while (quantidadeJogadores < 1 || quantidadeJogadores > 6) {
            System.out.println("Número de jogadores inválido. Por favor, selecione um número de jogadores entre 1 e 6.");
            quantidadeJogadores = teclado.nextInt();
            teclado.nextLine();  
        }

        ArrayList<Jogador> jogadores = new ArrayList<>();

        
        for (int i = 0; i < quantidadeJogadores; i++) {
            System.out.println("Digite a cor do " + (i + 1) + "º jogador: ");
            String cor = teclado.nextLine();

            System.out.println("Digite o tipo do jogador (azarado, normal, sortudo): ");
            String tipo = teclado.nextLine();

            Jogador jogador = JogadorFactory.criarJogador(tipo, cor, 0, 0, 0, 0);

            jogadores.add(jogador);
        }

        return jogadores;
    }

    public  void criacaoDoTabuleiro1(int totalCasas) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> casasEscolhidas = new ArrayList<>();
    
        
        tabuleiroJogado = new ArrayList<>(Collections.nCopies(totalCasas, null));
    
        
        String[] tiposDeCasas = {"surpresa", "sorte", "reversa", "prisao", "jogadenovo", "azar", "troca"};
    
        for (String tipo : tiposDeCasas) {
            System.out.println("Quantas casas do tipo " + tipo + " deseja criar?");
            int quantidade = scanner.nextInt();
            scanner.nextLine(); 
    
            for (int i = 0; i < quantidade; i++) {
                System.out.println("Digite a posição para a casa " + (i + 1) + " do tipo " + tipo + ":");
                int numero = scanner.nextInt();
                scanner.nextLine(); 
    
               
                while (casasEscolhidas.contains(numero) || numero < 0 || numero >= totalCasas) {
                    if (casasEscolhidas.contains(numero)) {
                        System.out.println("Posição já escolhida. Digite uma nova posição para a casa " + (i + 1) + " do tipo " + tipo + ":");
                    } else {
                        System.out.println("Posição inválida. Digite uma posição entre 0 e " + (totalCasas - 1) + ":");
                    }
                    numero = scanner.nextInt();
                    scanner.nextLine(); 
                }
    
                casasEscolhidas.add(numero); 
                
                Casa novaCasa;
                if (tipo.equals("sorte") || tipo.equals("reversa")) {
                    novaCasa = CasaFactory.criarCasa(tipo, numero, jogadores, tabuleiroJogado);
                } else {
                    novaCasa = CasaFactory.criarCasa(tipo, numero);
                }
    
               
                tabuleiroJogado.set(numero, novaCasa);
            }
        }
    
  
        for (int i = 0; i < totalCasas; i++) {
            if (tabuleiroJogado.get(i) == null) {
                tabuleiroJogado.set(i, CasaFactory.criarCasa("simples", i));
            }
        }
    
        System.out.println("Tabuleiro criado com sucesso!");
    }


   
    
    public void adicionarJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }



}

