public class Ordenador {

    private long comparacoes;
    private long trocas;
    private long tempoNano;

    public long getComparacoes() {
        return comparacoes;
    }

    public long getTrocas() {
        return trocas;
    }

    public long getTempoNano() {
        return tempoNano;
    }

    private void zerarContadores() {
        this.comparacoes = 0;
        this.trocas = 0;
        this.tempoNano = 0;
    }

    public void bubbleSort(Tripulante[] vetor, boolean passoAPasso) {
        zerarContadores();
        int n = vetor.length;
        long inicio = System.nanoTime();

        for (int i = 0; i < n - 1; i++) {
            if (passoAPasso) {
                System.out.println("\n★ Bubble Sort · passagem " + (i + 1));
            }

            for (int j = 0; j < n - 1 - i; j++) {
                comparacoes++;

                if (vetor[j].getCodigo() > vetor[j + 1].getCodigo()) {
                    if (passoAPasso) {
                        System.out.println("  " + vetor[j].getNome() + " (" + vetor[j].getCodigo() + ") > "
                                + vetor[j + 1].getNome() + " (" + vetor[j + 1].getCodigo() + ")? SIM -> trocam de assento!");
                    }

                    Tripulante temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocas++;
                } else {
                    if (passoAPasso) {
                        System.out.println("  " + vetor[j].getNome() + " (" + vetor[j].getCodigo() + ") > "
                                + vetor[j + 1].getNome() + " (" + vetor[j + 1].getCodigo() + ")? NÃO -> "
                                + vetor[j].getNome() + " fica onde está");
                    }
                }
            }

            if (passoAPasso) {
                System.out.println("  ... fim da passagem " + (i + 1)
                        + " -> comparações: " + comparacoes + " | trocas: " + trocas);
            }
        }

        this.tempoNano = System.nanoTime() - inicio;
    }

    public void insertionSort(Tripulante[] vetor, boolean passoAPasso) {
        zerarContadores();
        int n = vetor.length;
        long inicio = System.nanoTime();

        for (int i = 1; i < n; i++) {
            Tripulante emPe = vetor[i];
            int j = i - 1;

            if (passoAPasso) {
                System.out.println("\n★ Insertion Sort · vez de " + emPe.getNome() + " (" + emPe.getCodigo() + ")");
            }

            while (j >= 0) {
                comparacoes++;

                if (vetor[j].getCodigo() > emPe.getCodigo()) {
                    if (passoAPasso) {
                        System.out.println("  " + vetor[j].getNome() + " (" + vetor[j].getCodigo() + ") > "
                                + emPe.getNome() + " (" + emPe.getCodigo() + ")? SIM -> "
                                + vetor[j].getNome() + " escorrega para a direita!");
                    }

                    vetor[j + 1] = vetor[j];
                    trocas++;
                    j--;
                } else {
                    if (passoAPasso) {
                        System.out.println("  " + vetor[j].getNome() + " (" + vetor[j].getCodigo() + ") > "
                                + emPe.getNome() + " (" + emPe.getCodigo() + ")? NÃO -> achou a vaga!");
                    }
                    break;
                }
            }

            vetor[j + 1] = emPe;
            if (passoAPasso) {
                System.out.println("  -> " + emPe.getNome() + " sentou na cadeira " + (j + 1)
                        + " | comparações: " + comparacoes + " | deslocamentos: " + trocas);
            }
        }

        this.tempoNano = System.nanoTime() - inicio;
    }
}