import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class BancoDeDados {

    private Tripulante[] tripulantes;
    private int quantidadeAtual;

    public BancoDeDados() {
        this.tripulantes = new Tripulante[500];
        this.quantidadeAtual = 0;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public boolean codigoJaExiste(int codigoProcurado) {
        for (int i = 0; i < quantidadeAtual; i++) {
            if (tripulantes[i].getCodigo() == codigoProcurado) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarTripulante(int codigo, String nome, String planeta, String funcao) {
        if (codigo < 10000000 || codigo > 99999999) {
            System.out.println("Erro: O código deve ter 8 dígitos!");
            return false;
        }
        if (codigoJaExiste(codigo)) {
            System.out.println("Erro: Esse código já existe!");
            return false;
        }

        tripulantes[quantidadeAtual] = new Tripulante(codigo, nome, planeta, funcao);
        quantidadeAtual++;
        return true;
    }

    public Tripulante[] obterCopiaVetor() {
        Tripulante[] copia = new Tripulante[quantidadeAtual];
        for (int i = 0; i < quantidadeAtual; i++) {
            copia[i] = tripulantes[i].copiar();
        }
        return copia;
    }

    public Tripulante[] obterCopiaParcial(int limite) {
        Tripulante[] copia = new Tripulante[limite];
        for (int i = 0; i < limite; i++) {
            copia[i] = tripulantes[i].copiar();
        }
        return copia;
    }

    public static Tripulante[] clonarVetor(Tripulante[] original) {
        Tripulante[] copia = new Tripulante[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i].copiar();
        }
        return copia;
    }

    public void carregarArquivo(String caminhoArquivo) {
        quantidadeAtual = 0;
        try {
            Scanner leitor = new Scanner(new File(caminhoArquivo));
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] partes = linha.split(";");
                int codigo = Integer.parseInt(partes[0]);
                String nome = partes[1];
                String planeta = partes[2];
                String funcao = partes[3];
                registrarTripulante(codigo, nome, planeta, funcao);
            }
            leitor.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static Tripulante[] gerarTripulantesCorrida(int n, int situacao) {
        Tripulante[] fila = new Tripulante[n];
        Random sorteador = new Random(42);

        String[] nomes = {"Zibo Zumbido", "Quark Quarkinho", "Lumi Lunar", "Gloop Gelatina", "Cometa Clotilde"};
        String[] planetas = {"Marte", "Júpiter", "Plutão", "Vênus", "Saturno"};
        String[] funcoes = {"Piloto", "Engenheiro", "Navegadora", "Cozinheiro", "Cientista"};

        for (int i = 0; i < n; i++) {
            int codigo;
            if (situacao == 3) {
                codigo = 90000000 - i;
            } else {
                codigo = 10000000 + i;
            }

            String nome = nomes[sorteador.nextInt(5)];
            String planeta = planetas[sorteador.nextInt(5)];
            String funcao = funcoes[sorteador.nextInt(5)];

            fila[i] = new Tripulante(codigo, nome, planeta, funcao);
        }

        if (situacao == 1) {
            for (int i = 0; i < n; i++) {
                int cadeiraSorteada = sorteador.nextInt(n);
                Tripulante temp = fila[i];
                fila[i] = fila[cadeiraSorteada];
                fila[cadeiraSorteada] = temp;
            }
        }

        return fila;
    }
}