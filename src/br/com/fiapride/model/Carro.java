package br.com.fiapride.model;

/** Um Carro É UM Veículo: herda placa, modelo e as regras da mãe, e acrescenta o que é só dele. */
public class Carro extends Veiculo {

    private static final int CAPACIDADE_MAXIMA = 7;

    private int capacidadePassageiros;

    public Carro(String placa, String modelo, int capacidadePassageiros) {
        super(placa, modelo);   // a mãe valida a placa: a filha não mexe nos atributos privados dela
        this.setCapacidadePassageiros(capacidadePassageiros);
    }

    public int getCapacidadePassageiros() {
        return this.capacidadePassageiros;
    }

    private void setCapacidadePassageiros(int capacidadePassageiros) {
        if (capacidadePassageiros < 1 || capacidadePassageiros > CAPACIDADE_MAXIMA) {
            System.out.println("Erro de validação: um carro leva de 1 a " + CAPACIDADE_MAXIMA + " passageiros.");
            return;
        }
        this.capacidadePassageiros = capacidadePassageiros;
    }
}
