package br.com.fiapride.main;

import java.util.ArrayList;
import java.util.List;

import br.com.fiapride.model.Carro;
import br.com.fiapride.model.CarroEletrico;
import br.com.fiapride.model.Celular;
import br.com.fiapride.model.Moto;
import br.com.fiapride.model.Passageiro;
import br.com.fiapride.model.Recarregavel;
import br.com.fiapride.model.Veiculo;
import br.com.fiapride.model.Viagem;

public class SistemaPrincipal {

    public static void main(String[] args) {
        demonstrarPassageiros();
        demonstrarVeiculos();
        demonstrarViagem();
        demonstrarHeranca();
        demonstrarPolimorfismo();
        demonstrarClasseAbstrata();
        demonstrarInterface();
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

    /**
     * Aula 4: o veículo nasce válido, o modelo é imutável e a placa muda só por processo.
     * Desde a Aula 8 o Veiculo é abstrato, então o teste usa um Carro.
     */
    private static void demonstrarVeiculos() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Veículos ===");

        Carro corolla = new Carro("ABC-1234", "Toyota Corolla", 4);
        // Carro semDados = new Carro();            // não compila: The constructor Carro() is undefined
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
        Veiculo corolla = new Carro("ABC-1234", "Toyota Corolla", 4);

        Viagem viagemDaAna = new Viagem("Av. Paulista", ana, corolla);

        // Passagem por referência: a viagem aponta para o MESMO objeto ana, e não para uma cópia.
        ana.adicionarSaldo(30.0);
        System.out.println("Saldo visto pela viagem: R$ " + viagemDaAna.getSolicitante().getSaldo());

        viagemDaAna.finalizar(35.0);    // cobra da Ana pelo próprio objeto Passageiro
        viagemDaAna.exibirResumo();

        Viagem viagemCara = new Viagem("Aeroporto de Guarulhos", ana, corolla);
        viagemCara.finalizar(120.0);    // recusado: saldo insuficiente
    }

    /** Aula 6: Carro e Moto herdam de Veiculo e a Viagem aceita qualquer filha. */
    private static void demonstrarHeranca() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Herança ===");

        Carro civic = new Carro("CAR-1111", "Honda Civic", 4);
        Moto cg = new Moto("MOT-2222", "Honda CG 160", false);
        Carro carroLotado = new Carro("CAR-3333", "Fiat Uno", 12);   // recusado: capacidade inválida

        civic.atualizarPlaca("BRA3F45");    // método herdado da mãe
        System.out.println("Carro: " + civic.getModelo() + " | Placa: " + civic.getPlaca()
                + " | Lugares: " + civic.getCapacidadePassageiros());
        System.out.println("Moto: " + cg.getModelo() + " | Placa: " + cg.getPlaca() + " | Elétrica? " + cg.isEletrica());
        System.out.println("Lugares registrados no Uno após a validação: " + carroLotado.getCapacidadePassageiros());

        // A Viagem pede um Veiculo, então aceita Carro e Moto sem nenhuma mudança na classe Viagem.
        Passageiro bruno = new Passageiro("Bruno Lima", 40.0);
        Viagem viagemDeMoto = new Viagem("Vila Madalena", bruno, cg);
        viagemDeMoto.finalizar(18.0);
        viagemDeMoto.exibirResumo();
    }

    /** Aula 7: o mesmo comando, calcularAutonomia(), dá respostas diferentes para cada tipo. */
    private static void demonstrarPolimorfismo() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Relatório de Autonomia da Frota (Polimorfismo) ===");

        List<Veiculo> frota = new ArrayList<>();   // lista da mãe: aceita qualquer filha
        frota.add(new Carro("CAR-1234", "Honda Civic", 4));
        frota.add(new Moto("MOT-5678", "Honda CG 160", false));
        // frota.add(new Veiculo("GEN-0000", "Genérico"));  // desde a Aula 8 não compila: Veiculo is abstract

        for (Veiculo veiculo : frota) {
            veiculo.abastecer(50.0);
            // Sem if para descobrir o tipo: cada objeto responde do seu jeito.
            System.out.println(veiculo.getModelo() + " -> " + veiculo.calcularAutonomia());
        }
    }

    /** Aula 8: Veiculo é abstrato. Não existe "veículo genérico", só carros e motos de verdade. */
    private static void demonstrarClasseAbstrata() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Classe Abstrata ===");

        // Veiculo generico = new Veiculo("ABC-1234", "Genérico");
        // ERRO de compilação: Veiculo is abstract; cannot be instantiated

        Veiculo taxi = new Carro("ABC-1234", "Chevrolet Onix", 4);
        Veiculo mototaxi = new Moto("MOT-9999", "Honda Biz", false);

        System.out.println("Identificação dos veículos:");
        taxi.exibirTipo();
        mototaxi.exibirTipo();

        Veiculo[] frota = { taxi, mototaxi, new Carro("CAR-3333", "Honda Civic", 5) };
        for (Veiculo veiculo : frota) {
            veiculo.exibirTipo();   // método abstrato: toda filha concreta é obrigada a implementar
        }
    }

    /** Aula 9: CarroEletrico e Celular são de famílias diferentes, mas assinam o mesmo contrato Recarregavel. */
    private static void demonstrarInterface() {
        System.out.println();
        System.out.println("=== FIAPRIDE: Interfaces ===");

        CarroEletrico tesla = new CarroEletrico("TES-9999", "Tesla Model 3");
        Celular celularDoMotorista = new Celular("iPhone 15");

        // Polimorfismo de interface: os dois são tratados como Recarregavel.
        Recarregavel[] recarregaveis = { tesla, celularDoMotorista };
        System.out.println("Recarregando tudo para 85% (carga máxima permitida: " + Recarregavel.CARGA_MAXIMA + "%)");
        for (Recarregavel recarregavel : recarregaveis) {
            recarregavel.recarregar(85);    // mesmo método, comportamentos diferentes
        }
        tesla.recarregar(150);              // recusado: acima de CARGA_MAXIMA

        System.out.println();
        System.out.println("Tesla é um Veiculo? " + (tesla instanceof Veiculo));
        System.out.println("Tesla é Recarregavel? " + (tesla instanceof Recarregavel));
        System.out.println("Celular é um Veiculo? " + Veiculo.class.isInstance(celularDoMotorista));
        System.out.println("Celular é Recarregavel? " + (celularDoMotorista instanceof Recarregavel));

        System.out.println();
        tesla.exibirTipo();
        System.out.println(tesla.calcularAutonomia());
        System.out.println("Bateria do celular: " + celularDoMotorista.getNivelBateria() + "%");
    }
}
