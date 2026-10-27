package br.com.fiapride.main;

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
    }

    /** Aulas 1 a 4: objetos, métodos com validação, encapsulamento e construtores. */
    private static void demonstrarTeares() {
        System.out.println("=== TECELAGEM: Cadastro e Operação dos Teares ===");

        Tear tearNovo = new Tear();                                // construtor padrão
        Tear tear1 = new Tear("TEAR-01", "AirMax 500", 800);       // construtor completo
        Tear tear2 = new Tear("TEAR-02", "Rapier 220");            // this(): velocidade padrão
        Tear tearInvalido = new Tear("TEAR-99", "AirMax 500", 5000);   // recusado: velocidade fora da faixa

        // tear1.setModelo("Outro");                // não compila: o método não existe
        // tear1.metrosProduzidos = -50.0;          // não compila: o atributo é privado

        tearNovo.recadastrarCodigo("TEAR-03");
        tearNovo.recadastrarCodigo("");             // recusado: código vazio

        tear1.ajustarVelocidade(900);
        tear1.registrarProducao(120.5);
        tear1.registrarProducao(-10.0);             // recusado: produção negativa
        System.out.println("TEAR-01 atingiu a meta de 100 m? " + tear1.atingiuMeta(100.0));

        imprimirTear(tear1);
        imprimirTear(tear2);
        imprimirTear(tearNovo);
        imprimirTear(tearInvalido);
    }

    /** Aula 5: a ordem de produção TEM UM tear e registra a produção na própria máquina. */
    private static void demonstrarOrdensDeProducao() {
        System.out.println();
        System.out.println("=== TECELAGEM: Ordens de Produção ===");

        Tear tear1 = new Tear("TEAR-01", "AirMax 500", 800);
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

    private static void imprimirTear(Tear tear) {
        System.out.println(tear.getCodigo() + " | Modelo: " + tear.getModelo() + " | "
                + tear.getVelocidadeRpm() + " rpm | Produção: " + tear.getMetrosProduzidos() + " m");
    }
}
