package br.com.fiapride.model;

/**
 * Carro elétrico: É UM Veiculo (herança, uma única mãe) e TAMBÉM É Recarregavel (contrato).
 */
public class CarroEletrico extends Veiculo implements Recarregavel {

    private static final double KM_POR_PONTO_DE_CARGA = 5.0;

    private int nivelBateria;   // de 0 a 100 %

    public CarroEletrico(String placa, String modelo) {
        super(placa, modelo);
        this.setNivelBateria(Recarregavel.CARGA_MINIMA);
    }

    /** Implementação obrigatória do contrato Recarregavel. */
    @Override
    public void recarregar(int percentual) {
        if (percentual < Recarregavel.CARGA_MINIMA || percentual > Recarregavel.CARGA_MAXIMA) {
            System.out.println("Erro: percentual de carga inválido (" + percentual + "%).");
            return;
        }
        this.setNivelBateria(percentual);
        System.out.println("Carro " + this.getModelo() + " recarregado para " + percentual + "%");
    }

    /** Implementação obrigatória do método abstrato da mãe Veiculo. */
    @Override
    public void exibirTipo() {
        System.out.println("Sou um Carro Elétrico (" + this.getModelo() + ")");
    }

    /** A autonomia vem da bateria, e não do tanque: 5 km para cada ponto percentual de carga. */
    @Override
    public String calcularAutonomia() {
        double kmRestantes = this.nivelBateria * KM_POR_PONTO_DE_CARGA;
        return "Autonomia: " + kmRestantes + " km (bateria em " + this.nivelBateria + "%).";
    }

    public int getNivelBateria() {
        return this.nivelBateria;
    }

    /** Privado: a bateria só muda por recarregar(), que valida o percentual. */
    private void setNivelBateria(int nivelBateria) {
        this.nivelBateria = nivelBateria;
    }
}
