package br.com.fiapride.main;

import br.com.fiapride.model.Passageiro;

public class SistemaPrincipal {

    public static void main(String[] args) {
        System.out.println("=== FIAPRIDE: Passageiros e Saldo ===");

        Passageiro ana = new Passageiro();
        ana.nome = "Ana Silva";
        ana.saldo = 50.0;

        Passageiro carlos = new Passageiro();
        carlos.nome = "Carlos Souza";
        carlos.saldo = 12.5;

        ana.adicionarSaldo(20.0);
        ana.adicionarSaldo(-5.0);       // recusado: recarga inválida

        System.out.println("Ana pode pagar uma corrida de R$ 30? " + ana.temSaldoSuficiente(30.0));
        ana.pagarViagem(30.0);

        carlos.pagarViagem(20.0);       // recusado: saldo insuficiente
        carlos.pagarViagem(0.0);        // recusado: custo inválido
    }
}
