public class Tripulante {

    private int codigo;
    private String nome;
    private String planeta;
    private String funcao;

    public Tripulante(int codigo, String nome, String planeta, String funcao) {
        this.codigo = codigo;
        this.nome = nome;
        this.planeta = planeta;
        this.funcao = funcao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getPlaneta() {
        return planeta;
    }

    public String getFuncao() {
        return funcao;
    }

    public Tripulante copiar() {
        return new Tripulante(this.codigo, this.nome, this.planeta, this.funcao);
    }

    @Override
    public String toString() {
        return String.format("| %-10d | %-22s | %-12s | %-28s |", codigo, nome, planeta, funcao);
    }
}