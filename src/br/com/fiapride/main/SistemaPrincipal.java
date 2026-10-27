package br.com.fiapride.main;

import br.com.fiapride.model.Passageiro;

public class SistemaPrincipal {

    public static void main(String[] args) {
        System.out.println("=== FIAPRIDE: Passageiros e Saldo ===");

        Passageiro ana = new Passageiro();
        ana.setNome("Ana Silva");
        ana.adicionarSaldo(50.0);

        Passageiro carlos = new Passageiro();
        carlos.setNome("Carlos Souza");
        carlos.adicionarSaldo(12.5);

        // ana.saldo = 999999.0;        // não compila mais: saldo has private access in Passageiro
        // ana.setSaldo(999999.0);      // também não compila: o setter do saldo é privado

        carlos.setNome("");             // recusado: nome vazio
        ana.adicionarSaldo(-5.0);       // recusado: recarga inválida

        System.out.println("Ana pode pagar uma corrida de R$ 30? " + ana.temSaldoSuficiente(30.0));
        ana.pagarViagem(30.0);
        carlos.pagarViagem(20.0);       // recusado: saldo insuficiente

        System.out.println(ana.getNome() + " | Saldo: R$ " + ana.getSaldo());
        System.out.println(carlos.getNome() + " | Saldo: R$ " + carlos.getSaldo());
    }
}
