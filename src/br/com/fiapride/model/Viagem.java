package br.com.fiapride.model;

/**
 * Corrida do FiapRide. Associação (TEM-UM): uma viagem tem um passageiro e tem um veículo.
 * A viagem guarda os OBJETOS, e não apenas o nome e a placa, porque precisa
 * usar o comportamento deles, como cobrar a corrida do passageiro.
 */
public class Viagem {

    private String destino;
    private double valor;
    private Passageiro solicitante;
    private Veiculo veiculoUtilizado;

    public Viagem(String destino, Passageiro solicitante, Veiculo veiculoUtilizado) {
        this.setDestino(destino);
        this.setSolicitante(solicitante);
        this.setVeiculoUtilizado(veiculoUtilizado);
        this.setValor(0.0);
    }

    /** Encerra a corrida e cobra do solicitante. Só é possível porque a viagem conhece o objeto Passageiro. */
    public void finalizar(double valorDaCorrida) {
        if (valorDaCorrida <= 0) {
            System.out.println("Erro: o valor da corrida deve ser maior que zero.");
            return;
        }
        if (!this.solicitante.temSaldoSuficiente(valorDaCorrida)) {
            System.out.println("Viagem para " + this.destino + " não finalizada: "
                    + this.solicitante.getNome() + " não tem saldo suficiente.");
            return;
        }
        this.solicitante.pagarViagem(valorDaCorrida);
        this.setValor(valorDaCorrida);
    }

    public void exibirResumo() {
        System.out.println("Viagem para " + this.destino
                + " | Passageiro: " + this.solicitante.getNome()
                + " | Veículo: " + this.veiculoUtilizado.getModelo() + " (" + this.veiculoUtilizado.getPlaca() + ")"
                + " | Valor: R$ " + this.valor);
    }

    public String getDestino() {
        return this.destino;
    }

    public double getValor() {
        return this.valor;
    }

    public Passageiro getSolicitante() {
        return this.solicitante;
    }

    public Veiculo getVeiculoUtilizado() {
        return this.veiculoUtilizado;
    }

    private void setDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            System.out.println("Erro de validação: o destino não pode ser vazio.");
            return;
        }
        this.destino = destino;
    }

    private void setValor(double valor) {
        if (valor < 0) {
            System.out.println("Erro de validação: o valor da viagem não pode ser negativo.");
            return;
        }
        this.valor = valor;
    }

    private void setSolicitante(Passageiro solicitante) {
        if (solicitante == null) {
            System.out.println("Erro de validação: a viagem exige um passageiro.");
            return;
        }
        this.solicitante = solicitante;
    }

    private void setVeiculoUtilizado(Veiculo veiculoUtilizado) {
        if (veiculoUtilizado == null) {
            System.out.println("Erro de validação: a viagem exige um veículo.");
            return;
        }
        this.veiculoUtilizado = veiculoUtilizado;
    }
}
