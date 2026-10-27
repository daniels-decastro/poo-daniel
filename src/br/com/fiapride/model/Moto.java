package br.com.fiapride.model;

/** Uma Moto É UM Veículo: herda placa e modelo e acrescenta se é elétrica. */
public class Moto extends Veiculo {

    private boolean eletrica;

    public Moto(String placa, String modelo, boolean eletrica) {
        super(placa, modelo);
        this.eletrica = eletrica;
    }

    public boolean isEletrica() {
        return this.eletrica;
    }
}
