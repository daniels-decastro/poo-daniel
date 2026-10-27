package br.com.fiapride.pessoal;

/**
 * Tear de pinças: a trama é levada de um lado ao outro por pinças mecânicas.
 * É mais lento que o jato de ar, mas trabalha com mais cores e fios mais variados.
 * Um TearDePinca É UM Tear.
 */
public class TearDePinca extends Tear {

    private static final int MAXIMO_DE_CORES = 12;

    private int quantidadeDeCores;

    public TearDePinca(String codigo, String modelo, int quantidadeDeCores) {
        super(codigo, modelo);   // usa a velocidade padrão definida pela mãe
        this.setQuantidadeDeCores(quantidadeDeCores);
    }

    public int getQuantidadeDeCores() {
        return this.quantidadeDeCores;
    }

    private void setQuantidadeDeCores(int quantidadeDeCores) {
        if (quantidadeDeCores < 1 || quantidadeDeCores > MAXIMO_DE_CORES) {
            System.out.println("Erro de validação: o tear de pinças trabalha com 1 a " + MAXIMO_DE_CORES + " cores.");
            return;
        }
        this.quantidadeDeCores = quantidadeDeCores;
    }
}
