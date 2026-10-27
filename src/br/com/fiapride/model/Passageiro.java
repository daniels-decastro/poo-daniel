package br.com.fiapride.model;

/**
 * Passageiro do FiapRide.
 * Os métodos concentram as regras do saldo em um só lugar.
 */
public class Passageiro {

    public String nome;
    public double saldo;

    /** Recarrega créditos. Valor zero ou negativo não é recarga. */
    public void adicionarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: o valor da recarga deve ser maior que zero.");
            return;
        }
        this.saldo = this.saldo + valor;
        System.out.println("Recarga de R$ " + valor + " para " + this.nome + ". Saldo atual: R$ " + this.saldo);
    }

    /** Responde se o passageiro consegue pagar uma corrida desse valor. */
    public boolean temSaldoSuficiente(double custo) {
        return this.saldo >= custo;
    }

    /** Desconta a corrida, desde que o custo seja válido e o saldo cubra o valor. */
    public void pagarViagem(double custo) {
        if (custo <= 0) {
            System.out.println("Erro: o custo da viagem deve ser maior que zero.");
            return;
        }
        if (!this.temSaldoSuficiente(custo)) {
            System.out.println("Erro: " + this.nome + " não tem saldo para pagar R$ " + custo + ".");
            return;
        }
        this.saldo = this.saldo - custo;
        System.out.println("Viagem paga por " + this.nome + ". Saldo restante: R$ " + this.saldo);
    }
}
