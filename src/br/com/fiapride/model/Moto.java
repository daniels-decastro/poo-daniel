package br.com.fiapride.model;

/** Uma Moto É UM Veículo: herda placa e modelo e acrescenta se é elétrica. */
public class Moto extends Veiculo {

    private static final double KM_POR_LITRO = 35.0;

    private boolean eletrica;

    public Moto(String placa, String modelo, boolean eletrica) {
        super(placa, modelo);
        this.eletrica = eletrica;
    }

    /** A moto é mais leve e faz 35 km por litro. */
    @Override
    public String calcularAutonomia() {
        double kmRestantes = this.getNivelCombustivelEmLitros() * KM_POR_LITRO;
        return "Autonomia: " + kmRestantes + " km (consumo de " + KM_POR_LITRO + " km/l).";
    }

    public boolean isEletrica() {
        return this.eletrica;
    }
}
