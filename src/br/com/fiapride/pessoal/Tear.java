package br.com.fiapride.pessoal;

/**
 * Tear: máquina da tecelagem que cruza os fios de trama com os fios de urdume.
 * Os métodos protegem as regras de operação da máquina.
 */
public class Tear {

    // Faixa segura de operação. Constantes evitam "números mágicos" espalhados pelo código.
    private static final int VELOCIDADE_MINIMA_RPM = 100;
    private static final int VELOCIDADE_MAXIMA_RPM = 1200;

    public String codigo;
    public String modelo;
    public int velocidadeRpm;          // batidas (inserções de trama) por minuto
    public double metrosProduzidos;

    /** Ajusta a velocidade, desde que ela fique dentro da faixa segura da máquina. */
    public void ajustarVelocidade(int novaVelocidadeRpm) {
        if (novaVelocidadeRpm < VELOCIDADE_MINIMA_RPM || novaVelocidadeRpm > VELOCIDADE_MAXIMA_RPM) {
            System.out.println("Erro: " + novaVelocidadeRpm + " rpm está fora da faixa de "
                    + VELOCIDADE_MINIMA_RPM + " a " + VELOCIDADE_MAXIMA_RPM + " rpm.");
            return;
        }
        this.velocidadeRpm = novaVelocidadeRpm;
        System.out.println(this.codigo + " ajustado para " + this.velocidadeRpm + " rpm.");
    }

    /** Soma ao contador o tecido produzido. Produção zero ou negativa não existe. */
    public void registrarProducao(double metros) {
        if (metros <= 0) {
            System.out.println("Erro: a produção registrada deve ser maior que zero.");
            return;
        }
        this.metrosProduzidos = this.metrosProduzidos + metros;
        System.out.println(this.codigo + " registrou " + metros + " m. Total: " + this.metrosProduzidos + " m.");
    }

    /** Compara a produção acumulada com a meta do turno. */
    public boolean atingiuMeta(double metaEmMetros) {
        if (metaEmMetros <= 0) {
            System.out.println("Erro: a meta deve ser maior que zero.");
            return false;
        }
        return this.metrosProduzidos >= metaEmMetros;
    }
}
