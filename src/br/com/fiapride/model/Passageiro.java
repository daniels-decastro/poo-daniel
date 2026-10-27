package br.com.fiapride.model;

/**
 * Passageiro do FiapRide.
 * Atributos privados: ninguém de fora altera o saldo sem passar pelas regras.
 * Construtores: todo passageiro nasce com nome válido e saldo nunca negativo.
 */
public class Passageiro {

    private String nome;
    private double saldo;

    /** Cadastro comum: o passageiro começa sem créditos. Delega para o construtor completo com this(). */
    public Passageiro(String nome) {
        this(nome, 0.0);
    }

    /** Cadastro com crédito inicial (por exemplo, um bônus de boas-vindas). */
    public Passageiro(String nome, double saldoInicial) {
        this.setNome(nome);
        this.setSaldo(saldoInicial);
    }

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

    /** Agora privado: o nome é definido no nascimento do objeto, pelo construtor. */
    private void setNome(String nome) {
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
