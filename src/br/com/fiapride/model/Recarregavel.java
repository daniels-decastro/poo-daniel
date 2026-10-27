package br.com.fiapride.model;

/**
 * Contrato de comportamento: tudo que tem bateria e pode ser recarregado,
 * seja um veículo (CarroEletrico) ou não (Celular).
 * Quem implementa deve aceitar um percentual entre CARGA_MINIMA e CARGA_MAXIMA.
 */
public interface Recarregavel {

    // Constantes de interface: implicitamente public static final.
    int CARGA_MAXIMA = 100;
    int CARGA_MINIMA = 0;

    // Método de interface: implicitamente public abstract (só a assinatura, sem corpo).
    void recarregar(int percentual);
}
