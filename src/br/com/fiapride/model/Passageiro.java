package br.com.fiapride.model;

/**
 * Passageiro do FiapRide.
 * Atributos privados: ninguém de fora altera o saldo sem passar pelas regras.
 */
public class Passageiro {

    private String nome;
    private double saldo;

    /** Recarrega créditos. Valor zero ou negativo não é recarga. */
    public void adicionarSaldo(double valor) {
        if (valor <= 0) {
            System.out.println("Erro: o valor da recarga deve ser maior que zero.");
            return;
        }
        this.setSaldo(this.saldo + valor);
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
        this.setSaldo(this.saldo - custo);
        System.out.println("Viagem paga por " + this.nome + ". Saldo restante: R$ " + this.saldo);
    }

    public String getNome() {
        return this.nome;
    }

    public double getSaldo() {
        return this.saldo;
    }

    /** Público por enquanto: é a única forma de registrar o nome enquanto não existe construtor. */
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro de validação: o nome do passageiro não pode ser vazio.");
            return;
        }
        this.nome = nome;
    }

    /** Privado: dinheiro só entra por adicionarSaldo() e só sai por pagarViagem(). */
    private void setSaldo(double saldo) {
        if (saldo < 0) {
            System.out.println("Erro de segurança: saldo negativo bloqueado.");
            return;
        }
        this.saldo = saldo;
    }
}
