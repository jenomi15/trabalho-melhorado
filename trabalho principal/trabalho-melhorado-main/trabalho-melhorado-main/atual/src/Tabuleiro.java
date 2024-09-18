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
                if (tipo.equals("sorte") || tipo.equals("reversa") || tipo.equals("surpresa")) {
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
                    jogadorAtual.setPulaProximaRodada(jogadorAtual.getPulaProximaRodada()-1);
                    continue; 
                }
    
                // Processo de rolar dados e mover jogador
                System.out.println("Turno do jogador N* " + (a + 1) + " (" + jogadorAtual.getCor() + " que possui um número de moedas = (" + jogadorAtual.getPontuacaoEmMoedas() + "), role os dados.");
                ResultadoDados resultado = jogadorAtual.rolarDados();
                int novaPosicao = posicaoAntiga + resultado.getSoma();
                if (novaPosicao >= tabuleiroJogado.size()) {
                    novaPosicao = tabuleiroJogado.size() - 1;
                }
    
                System.out.println("\n Posição antiga do jogador " + jogadorAtual.getCor() + " : " + posicaoAntiga);
                System.out.println("Nova posição calculada para o jogador " + jogadorAtual.getCor() + " : " + novaPosicao);
    
                // Atualiza a posição do jogador no tabuleiro
                removerCorCasa(posicaoAntiga, jogadorAtual.getCor());
                jogadorAtual.setNumeroCasa(novaPosicao);
                adicionarCorCasa(novaPosicao, jogadorAtual.getCor());
    
                // Aplica a regra da casa em que o jogador parou
                tabuleiroJogado.get(novaPosicao).aplicarRegra(jogadorAtual);
                 
                if (novaPosicao != jogadores.get(a).getNumeroCasa()) {
                    removerCorCasa(novaPosicao, jogadores.get(a).getCor());
                    adicionarCorCasa(jogadores.get(a).getNumeroCasa(), jogadores.get(a).getCor());
                }
                if (novaPosicao > tabuleiroJogado.size()) {
                    novaPosicao = tabuleiroJogado.size() - 1;  
                    
                }
    
                // Imprime o estado atual do tabuleiro
                imprimirTabuleiro();
    
                // Verifica se o jogador deve jogar novamente
                if (jogadorAtual.isDeveJogarNovamente()) {
                    removerCorCasa(novaPosicao, jogadorAtual.getCor());
                    jogadorAtual.setDeveJogarNovamente(false);
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
            System.out.println((i + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa() + " - Tipo: " + jogador.getTipo() + " - Moedas: " + jogador.getPontuacaoEmMoedas() + " - NUMERO de jogadas : " +jogador.getNumeroDeJogadas() );
    }
    
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
    public void TurnoDoJogoDebug() {
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
                        System.out.println((w + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa() + " - Tipo: " + jogador.getTipoDescricao() + " - Moedas: " + jogador.getPontuacaoEmMoedas());
                    }
                    teclado1.close();
                    return; // Encerra o método e o jogo
                }
    
                // Verifica se o jogador está preso
                if (jogadorAtual.pulaRodada() > 0) {
                    if (jogadorAtual.getPulaProximaRodada() == 2) {
                        // Apenas pula o turno se restarem 2 rodadas de prisão
                        System.out.println("O jogador " + jogadorAtual.getCor() + " está preso e não jogará este turno. Restam " + jogadorAtual.getPulaProximaRodada() + " rodadas.");
                        jogadorAtual.setPulaProximaRodada(jogadorAtual.getPulaProximaRodada() - 1);
                        continue; // Pula o turno
                    } else if (jogadorAtual.getPulaProximaRodada() == 1) {
                        // Oferece a opção de pagar a taxa quando restar 1 rodada
                        System.out.println("O jogador " + jogadorAtual.getCor() + " está preso! Deseja pagar uma taxa de 2 moedas para sair da prisão agora? (1 - Sim, 2 - Não)");
                        int escolha = teclado1.nextInt();
    
                        if (escolha == 1) {
                            if (jogadorAtual.getPontuacaoEmMoedas() >= 2) {
                                jogadorAtual.removerMoeda(2);
                                jogadorAtual.setPulaProximaRodada(0); // Libera o jogador
                                System.out.println("O jogador " + jogadorAtual.getCor() + " pagou a taxa e saiu da prisão.");
                            } else {
                                System.out.println("O jogador " + jogadorAtual.getCor() + " não tem moedas suficientes para pagar a taxa.");
                                jogadorAtual.setPulaProximaRodada(jogadorAtual.getPulaProximaRodada() - 1); // Continua preso
                                continue; // Pula o turno
                            }
                        } else {
                            // O jogador escolheu não pagar, então continua preso
                            System.out.println("O jogador " + jogadorAtual.getCor() + " escolheu não pagar. Ainda está preso por " + jogadorAtual.getPulaProximaRodada() + " rodada(s).");
                            jogadorAtual.setPulaProximaRodada(jogadorAtual.getPulaProximaRodada() - 1);
                            continue; // Pula o turno
                        }
                    }
                }
    
                // Solicita a posição da casa para a qual o jogador deve ir
                System.out.println("Turno do jogador N* " + (a + 1) + " (" + jogadorAtual.getCor() + ", Tipo: " + jogadorAtual.getTipoDescricao() + ", Moedas: " + jogadorAtual.getPontuacaoEmMoedas() + ").");
                System.out.println("Digite a posição da casa para a qual o jogador deve ir (0 a " + (tabuleiroJogado.size() - 1) + "):");
                int novaPosicao = teclado1.nextInt();
    
                // Verifica se a nova posição é válida
                if (novaPosicao < 0 || novaPosicao >= tabuleiroJogado.size()) {
                    System.out.println("Posição inválida! O jogador permanecerá na posição atual.");
                    novaPosicao = posicaoAntiga;
                }
    
                System.out.println("\nPosição antiga do jogador " + jogadorAtual.getCor() + ": " + posicaoAntiga);
                System.out.println("Nova posição calculada para o jogador " + jogadorAtual.getCor() + ": " + novaPosicao);
    
                // Atualiza a posição do jogador no tabuleiro
                removerCorCasa(posicaoAntiga, jogadorAtual.getCor());
                jogadorAtual.setNumeroCasa(novaPosicao);
                adicionarCorCasa(novaPosicao, jogadorAtual.getCor());
    
                tabuleiroJogado.get(novaPosicao).aplicarRegra(jogadorAtual);
    
                if (novaPosicao != jogadores.get(a).getNumeroCasa()) {
                    removerCorCasa(novaPosicao, jogadores.get(a).getCor());
                    adicionarCorCasa(jogadores.get(a).getNumeroCasa(), jogadores.get(a).getCor());
                }
    
                if (novaPosicao > tabuleiroJogado.size()) {
                    novaPosicao = tabuleiroJogado.size() - 1;
                }
    
                imprimirTabuleiro();
    
                // Verifica se o jogador deve jogar novamente
                if (jogadorAtual.isDeveJogarNovamente()) {
                    removerCorCasa(novaPosicao, jogadorAtual.getCor());
                    jogadorAtual.setDeveJogarNovamente(false);
                    a--; // Repetir turno do mesmo jogador
                    continue;
                }
    
                // Pergunta se o jogador deseja continuar
                System.out.println("\nDeseja continuar? \n1 - Sim \n2 - Não");
                int p = teclado1.nextInt();
                if (p == 2) {
                    teclado1.close();
                    return; // Encerra o método e o jogo
                }
            }
        }
    
        // Se o loop terminar, imprime as posições finais
        Collections.sort(jogadores, Comparator.comparingInt(Jogador::getNumeroCasa).reversed());
    
        System.out.println("Posições dos jogadores:");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            System.out.println((i + 1) + "º lugar: Jogador " + jogador.getCor() + " - Casa " + jogador.getNumeroCasa() + " - Tipo: " + jogador.getTipoDescricao() + " - Moedas: " + jogador.getPontuacaoEmMoedas() + " - Número de jogadas: " + jogador.getNumeroDeJogadas());
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

            Jogador jogador = JogadorFactory.criarJogador(tipo, cor, 0, 0, 0, 0, false , false , false);

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
