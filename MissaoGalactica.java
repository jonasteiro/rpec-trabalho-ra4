import java.util.Scanner;

public class MissaoGalactica {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoDeDados banco = new BancoDeDados();
        Ordenador ordenador = new Ordenador();

        banco.carregarArquivo("tripulantes.txt");

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 1) {
                registrarTripulanteMenu(scanner, banco);
            } else if (opcao == 2) {
                listarTripulantesMenu(banco.obterCopiaVetor());
            } else if (opcao == 3) {
                organizarEmbarqueCapsula(banco, ordenador);
            } else if (opcao == 4) {
                executarModoPassoAPasso(scanner, banco, ordenador);
            } else if (opcao == 5) {
                executarGrandeCorrida(ordenador);
            } else if (opcao == 0) {
                System.out.println("Fim da Missão Galáctica!");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║     ★ ESTAÇÃO ESPACIAL ÓRBITA-7 ★         ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ 1 - Registrar tripulante                  ║");
        System.out.println("║ 2 - Listar tripulantes                    ║");
        System.out.println("║ 3 - Organizar embarque na cápsula         ║");
        System.out.println("║ 4 - Modo passo a passo (até 10)           ║");
        System.out.println("║ 5 - A Grande Corrida (50 / 1.000 / 10.000)║");
        System.out.println("║ 0 - Encerrar a missão                     ║");
        System.out.println("╚═══════════════════════════════════════════╝");
    }

    private static void registrarTripulanteMenu(Scanner scanner, BancoDeDados banco) {
        System.out.print("Código Galáctico (8 dígitos): ");
        int codigo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome do tripulante: ");
        String nome = scanner.nextLine();

        System.out.print("Planeta natal: ");
        String planeta = scanner.nextLine();

        System.out.print("Função a bordo: ");
        String funcao = scanner.nextLine();

        if (banco.registrarTripulante(codigo, nome, planeta, funcao)) {
            System.out.println("Tripulante registrado com sucesso!");
        }
    }

    private static void listarTripulantesMenu(Tripulante[] lista) {
        System.out.println("\n| CÓDIGO     | NOME                   | PLANETA      | FUNÇÃO A BORDO               |");
        for (int i = 0; i < lista.length; i++) {
            System.out.println(lista[i].toString());
        }
    }

    private static void organizarEmbarqueCapsula(BancoDeDados banco, Ordenador ordenador) {
        Tripulante[] copiaBubble = banco.obterCopiaVetor();
        Tripulante[] copiaInsertion = banco.obterCopiaVetor();
        Tripulante[] copiaQuick = banco.obterCopiaVetor();

        ordenador.bubbleSort(copiaBubble, false);
        System.out.println("Bubble Sort -> Comparações: " + ordenador.getComparacoes()
                + " | Trocas: " + ordenador.getTrocas() + " | Tempo (ns): " + ordenador.getTempoNano());

        ordenador.insertionSort(copiaInsertion, false);
        System.out.println("Insertion Sort -> Comparações: " + ordenador.getComparacoes()
                + " | Trocas: " + ordenador.getTrocas() + " | Tempo (ns): " + ordenador.getTempoNano());

        ordenador.quickSort(copiaQuick, false);
        System.out.println("Quick Sort -> Comparações: " + ordenador.getComparacoes()
                + " | Trocas: " + ordenador.getTrocas() + " | Tempo (ns): " + ordenador.getTempoNano());

        listarTripulantesMenu(copiaQuick);
    }

    private static void executarModoPassoAPasso(Scanner scanner, BancoDeDados banco, Ordenador ordenador) {
        System.out.print("Quantos tripulantes deseja simular (de 2 a 10)? ");
        int qtd = Integer.parseInt(scanner.nextLine());

        System.out.print("Qual algoritmo (1=Bubble, 2=Insertion, 3=Quick)? ");
        int alg = Integer.parseInt(scanner.nextLine());

        Tripulante[] amostra = banco.obterCopiaParcial(qtd);
        listarTripulantesMenu(amostra);

        if (alg == 1) {
            ordenador.bubbleSort(amostra, true);
        } else if (alg == 2) {
            ordenador.insertionSort(amostra, true);
        } else if (alg == 3) {
            ordenador.quickSort(amostra, true);
        }

        listarTripulantesMenu(amostra);
    }

    private static void executarGrandeCorrida(Ordenador ordenador) {
        int[] tamanhos = {50, 1000, 10000};
        String[] nomesSituacoes = {"", "Aleatório", "Já ordenado", "Ordem inversa"};

        for (int t = 0; t < 3; t++) {
            int n = tamanhos[t];

            for (int situacao = 1; situacao <= 3; situacao++) {
                System.out.println("\n=== GRANDE CORRIDA: n = " + n + " | Situação: " + nomesSituacoes[situacao] + " ===");

                Tripulante[] base = BancoDeDados.gerarTripulantesCorrida(n, situacao);

                Tripulante[] copiaBubble = BancoDeDados.clonarVetor(base);
                Tripulante[] copiaInsertion = BancoDeDados.clonarVetor(base);
                Tripulante[] copiaQuick = BancoDeDados.clonarVetor(base);

                ordenador.bubbleSort(copiaBubble, false);
                System.out.println("Bubble Sort    -> Tempo(ns): " + ordenador.getTempoNano()
                        + " | Comparações: " + ordenador.getComparacoes() + " | Trocas: " + ordenador.getTrocas());

                ordenador.insertionSort(copiaInsertion, false);
                System.out.println("Insertion Sort -> Tempo(ns): " + ordenador.getTempoNano()
                        + " | Comparações: " + ordenador.getComparacoes() + " | Trocas: " + ordenador.getTrocas());

                ordenador.quickSort(copiaQuick, false);
                System.out.println("Quick Sort     -> Tempo(ns): " + ordenador.getTempoNano()
                        + " | Comparações: " + ordenador.getComparacoes() + " | Trocas: " + ordenador.getTrocas());
            }
        }
    }
}