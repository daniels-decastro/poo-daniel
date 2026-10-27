package br.com.fiapride.pessoal;

/**
 * Tear a jato de ar: a trama atravessa o tecido levada por um sopro de ar comprimido.
 * É o tipo mais rápido. Um TearJatoDeAr É UM Tear.
 */
public class TearJatoDeAr extends Tear {

    private static final int VELOCIDADE_INICIAL_RPM = 800;
    private static final double PRESSAO_MINIMA_BAR = 2.0;
    private static final double PRESSAO_MAXIMA_BAR = 8.0;
    private static final double EFICIENCIA = 0.92;

    private double pressaoDoArEmBar;

    public TearJatoDeAr(String codigo, String modelo, double pressaoDoArEmBar) {
        super(codigo, modelo, VELOCIDADE_INICIAL_RPM);   // a mãe valida código, modelo e velocidade
        this.setPressaoDoArEmBar(pressaoDoArEmBar);
    }

    /** O jato de ar para pouco: eficiência típica de 92% do tempo produzindo. */
    @Override
    public String calcularProducaoPorHora() {
        double metrosPorHora = this.estimarMetrosPorHora(EFICIENCIA);
        return String.format("Produção estimada: %.1f m/h (jato de ar, eficiência de %.0f%%).",
                metrosPorHora, EFICIENCIA * 100);
    }

    public double getPressaoDoArEmBar() {
        return this.pressaoDoArEmBar;
    }

    private void setPressaoDoArEmBar(double pressaoDoArEmBar) {
        if (pressaoDoArEmBar < PRESSAO_MINIMA_BAR || pressaoDoArEmBar > PRESSAO_MAXIMA_BAR) {
            System.out.println("Erro de validação: a pressão do ar deve ficar entre "
                    + PRESSAO_MINIMA_BAR + " e " + PRESSAO_MAXIMA_BAR + " bar.");
            return;
        }
        this.pressaoDoArEmBar = pressaoDoArEmBar;
    }
}
