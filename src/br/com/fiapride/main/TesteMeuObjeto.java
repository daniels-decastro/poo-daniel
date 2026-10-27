package br.com.fiapride.main;

import java.util.ArrayList;
import java.util.List;

import br.com.fiapride.pessoal.Compressor;
import br.com.fiapride.pessoal.Manutenivel;
import br.com.fiapride.pessoal.OrdemDeProducao;
import br.com.fiapride.pessoal.Tear;
import br.com.fiapride.pessoal.TearDePinca;
import br.com.fiapride.pessoal.TearJatoDeAr;

/** Testes do projeto pessoal: controle de teares de uma tecelagem. */
public class TesteMeuObjeto {

    public static void main(String[] args) {
        demonstrarTeares();
        demonstrarOrdensDeProducao();
        demonstrarHeranca();
        demonstrarPolimorfismo();
        demonstrarClasseAbstrata();
        demonstrarInterface();
    }

    /**
     * Aulas 1 a 4: objetos, métodos com validação, encapsulamento e construtores.
     * Desde a Aula 8 o Tear é abstrato, então os testes usam os tipos concretos.
     */
    private static void demonstrarTeares() {
        System.out.println("=== TECELAGEM: Cadastro e Operação dos Teares ===");

        Tear tear1 = new TearJatoDeAr("TEAR-01", "AirMax 500", 6.0);
        Tear tear2 = new TearDePinca("TEAR-02", "Rapier 220", 4);

        // tear1.setModelo("Outro");                // não compila: o método não existe
        // tear1.metrosProduzidos = -50.0;          // não compila: o atributo é privado

        tear2.recadastrarCodigo("TEAR-03");
        tear2.recadastrarCodigo("");                // recusado: código vazio

        tear1.ajustarVelocidade(900);
        tear1.ajustarVelocidade(5000);              // recusado: acima da faixa segura
        tear1.registrarProducao(120.5);
        tear1.registrarProducao(-10.0);             // recusado: produção negativa
        System.out.println("TEAR-01 atingiu a meta de 100 m? " + tear1.atingiuMeta(100.0));

        imprimirTear(tear1);
        imprimirTear(tear2);
    }

    /** Aula 5: a ordem de produção TEM UM tear e registra a produção na própria máquina. */
    private static void demonstrarOrdensDeProducao() {
        System.out.println();
        System.out.println("=== TECELAGEM: Ordens de Produção ===");

        Tear tear1 = new TearJatoDeAr("TEAR-01", "AirMax 500", 6.0);
        OrdemDeProducao ordem = new OrdemDeProducao("OP-1001", 250.0, tear1);

        ordem.exibirResumo();
        ordem.executar();
        ordem.executar();               // recusado: a ordem já foi concluída
        ordem.exibirResumo();

        // Passagem por referência: a ordem alterou o MESMO objeto tear1 que está aqui no main.
        System.out.println("Produção do TEAR-01 vista pelo main: " + tear1.getMetrosProduzidos() + " m");

        OrdemDeProducao ordemInvalida = new OrdemDeProducao("OP-1002", -40.0, tear1);   // recusado: metragem inválida
        System.out.println("Metragem da OP-1002 após a validação: " + ordemInvalida.getMetrosPlanejados() + " m");
    }

    /** Aula 6: TearJatoDeAr e TearDePinca herdam de Tear e a ordem aceita qualquer filha. */
    private static void demonstrarHeranca() {
        System.out.println();
        System.out.println("=== TECELAGEM: Tipos de Tear (Herança) ===");

        TearJatoDeAr jato = new TearJatoDeAr("TEAR-10", "AirMax 500", 6.0);
        TearDePinca pinca = new TearDePinca("TEAR-20", "Rapier 220", 8);
        TearJatoDeAr jatoSemAr = new TearJatoDeAr("TEAR-11", "AirMax 500", 15.0);   // recusado: pressão fora da faixa

        jato.ajustarVelocidade(950);        // método herdado da mãe
        System.out.println(jato.getCodigo() + " | " + jato.getModelo() + " | " + jato.getVelocidadeRpm()
                + " rpm | Pressão do ar: " + jato.getPressaoDoArEmBar() + " bar");
        System.out.println(pinca.getCodigo() + " | " + pinca.getModelo() + " | " + pinca.getVelocidadeRpm()
                + " rpm | Cores: " + pinca.getQuantidadeDeCores());
        System.out.println("Pressão registrada no TEAR-11 após a validação: " + jatoSemAr.getPressaoDoArEmBar() + " bar");

        // A ordem pede um Tear, então aceita qualquer tipo de tear sem mudar a classe OrdemDeProducao.
        new OrdemDeProducao("OP-2001", 180.0, jato).executar();
        new OrdemDeProducao("OP-2002", 95.0, pinca).executar();
    }

    /** Aula 7: o mesmo comando, calcularProducaoPorHora(), dá respostas diferentes para cada tipo. */
    private static void demonstrarPolimorfismo() {
        System.out.println();
        System.out.println("=== TECELAGEM: Produção Estimada do Parque de Teares (Polimorfismo) ===");

        List<Tear> parqueDeTeares = new ArrayList<>();   // lista da mãe: aceita qualquer filha
        parqueDeTeares.add(new TearJatoDeAr("TEAR-10", "AirMax 500", 6.0));
        parqueDeTeares.add(new TearDePinca("TEAR-20", "Rapier 220", 8));
        // parqueDeTeares.add(new Tear());   // desde a Aula 8 não compila: Tear is abstract

        for (Tear tear : parqueDeTeares) {
            // Sem if para descobrir o tipo: cada objeto responde do seu jeito.
            System.out.println(tear.getCodigo() + " (" + tear.getVelocidadeRpm() + " rpm) -> "
                    + tear.calcularProducaoPorHora());
        }
    }

    /** Aula 8: Tear é abstrato. Toda filha é obrigada a implementar os métodos abstratos. */
    private static void demonstrarClasseAbstrata() {
        System.out.println();
        System.out.println("=== TECELAGEM: Classe Abstrata ===");

        // Tear generico = new Tear("TEAR-00", "Genérico");
        // ERRO de compilação: Tear is abstract; cannot be instantiated

        Tear[] parqueDeTeares = {
            new TearJatoDeAr("TEAR-10", "AirMax 500", 6.0),
            new TearDePinca("TEAR-20", "Rapier 220", 8),
            new TearJatoDeAr("TEAR-12", "AirMax 700", 5.5)
        };
        for (Tear tear : parqueDeTeares) {
            tear.exibirTipoDeInsercao();
        }
    }

    /** Aula 9: teares e compressor são de famílias diferentes, mas assinam o mesmo contrato Manutenivel. */
    private static void demonstrarInterface() {
        System.out.println();
        System.out.println("=== TECELAGEM: Plano de Manutenção Preventiva (Interface) ===");
        System.out.println("Intervalo de manutenção: " + Manutenivel.INTERVALO_MANUTENCAO_HORAS + " h");

        TearJatoDeAr jato = new TearJatoDeAr("TEAR-10", "AirMax 500", 6.0);
        TearDePinca pinca = new TearDePinca("TEAR-20", "Rapier 220", 8);
        Compressor compressor = new Compressor("COMP-01", 75);

        jato.registrarHorasDeUso(2150);
        pinca.registrarHorasDeUso(1200);
        compressor.registrarHorasDeUso(2400);

        Manutenivel[] equipamentos = { jato, pinca, compressor };
        for (Manutenivel equipamento : equipamentos) {
            if (equipamento.precisaDeManutencao()) {
                equipamento.realizarManutencao();
            } else {
                System.out.println("Equipamento em dia: ainda não atingiu o intervalo de manutenção.");
            }
        }

        System.out.println();
        System.out.println("TEAR-10 é um Tear? " + (jato instanceof Tear));
        System.out.println("TEAR-10 é Manutenivel? " + (jato instanceof Manutenivel));
        System.out.println("COMP-01 é um Tear? " + Tear.class.isInstance(compressor));
        System.out.println("COMP-01 é Manutenivel? " + (compressor instanceof Manutenivel));
    }

    private static void imprimirTear(Tear tear) {
        System.out.println(tear.getCodigo() + " | Modelo: " + tear.getModelo() + " | "
                + tear.getVelocidadeRpm() + " rpm | Produção: " + tear.getMetrosProduzidos() + " m");
    }
}
