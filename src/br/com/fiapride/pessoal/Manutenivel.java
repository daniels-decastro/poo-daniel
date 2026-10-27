package br.com.fiapride.pessoal;

/**
 * Contrato de comportamento: equipamento da fábrica que passa por manutenção preventiva,
 * seja um tear (TearJatoDeAr, TearDePinca) ou não (Compressor).
 * Quem implementa deve avisar quando atingiu o intervalo e saber fazer a própria manutenção.
 */
public interface Manutenivel {

    /** Horas de uso entre duas manutenções preventivas. Implicitamente public static final. */
    int INTERVALO_MANUTENCAO_HORAS = 2000;

    boolean precisaDeManutencao();

    void realizarManutencao();
}
