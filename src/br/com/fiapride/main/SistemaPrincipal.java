package br.com.fiapride.main;

import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Veiculo;
import br.com.fiapride.model.Viagem;

public class SistemaPrincipal {

    public static void main(String[] args) {
        demonstrarPassageiros();
        demonstrarVeiculos();
        demonstrarViagem();
    }

    /** Aulas 1 a 4: objetos, métodos com validação, encapsulamento e construtores. */
    private static void demonstrarPassageiros() {
        System.out.println("=== FIAPRIDE: Passageiros e Saldo ===");

        Passageiro ana = new Passageiro("Ana Silva", 50.0);
        Passageiro carlos = new Passageiro("Carlos Souza");   // this(): nasce com saldo zero

        // Passageiro fantasma = new Passageiro();  // não compila: The constructor Passageiro() is undefined
        // ana.saldo = 999999.0;                    // não compila: saldo has private access in Passageiro

        carlos.adicionarSaldo(12.5);
        ana.adicionarSaldo(-5.0);       // recusado: recarga inválida

        System.out.println("Ana pode pagar uma corrida de R$ 30? " + ana.temSaldoSuficiente(30.0));
        ana.pagarViagem(30.0);
        carlos.pagarViagem(20.0);       // recusado: saldo insuficiente

        System.out.println(ana.getNome() + " | Saldo: R$ " + ana.getSaldo());
        System.out.println(carlos.getNome() + " | Saldo: R$ " + carlos.getSaldo());
    }

    /** Aula 4: o veículo nasce válido, o modelo é imutável e a placa muda só por processo. */
    private static void demonstrarVeiculos() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Veículos ===");

        Veiculo corolla = new Veiculo("ABC-1234", "Toyota Corolla");
        // Veiculo semDados = new Veiculo();        // não compila: The constructor Veiculo() is undefined
        // corolla.setModelo("Fusca");              // não compila: o método não existe

        corolla.atualizarPlaca("BRA2E19");
        corolla.atualizarPlaca("");     // recusado: placa vazia

        System.out.println("Veículo: " + corolla.getModelo() + " | Placa: " + corolla.getPlaca());
    }

    /** Aula 5: a viagem TEM UM passageiro e TEM UM veículo, e usa o comportamento deles. */
    private static void demonstrarViagem() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Viagens ===");

        Passageiro ana = new Passageiro("Ana Silva", 20.0);
        Veiculo corolla = new Veiculo("ABC-1234", "Toyota Corolla");

        Viagem viagemDaAna = new Viagem("Av. Paulista", ana, corolla);

        // Passagem por referência: a viagem aponta para o MESMO objeto ana, e não para uma cópia.
        ana.adicionarSaldo(30.0);
        System.out.println("Saldo visto pela viagem: R$ " + viagemDaAna.getSolicitante().getSaldo());

        viagemDaAna.finalizar(35.0);    // cobra da Ana pelo próprio objeto Passageiro
        viagemDaAna.exibirResumo();

        Viagem viagemCara = new Viagem("Aeroporto de Guarulhos", ana, corolla);
        viagemCara.finalizar(120.0);    // recusado: saldo insuficiente
    }
}
