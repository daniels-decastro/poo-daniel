package br.com.fiapride.main;

import br.com.fiapride.pessoal.Tear;

/** Testes do projeto pessoal: controle de teares de uma tecelagem. */
public class TesteMeuObjeto {

    public static void main(String[] args) {
        demonstrarTeares();
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

    private static void imprimirTear(Tear tear) {
        System.out.println(tear.getCodigo() + " | Modelo: " + tear.getModelo() + " | "
                + tear.getVelocidadeRpm() + " rpm | Produção: " + tear.getMetrosProduzidos() + " m");
    }
}
