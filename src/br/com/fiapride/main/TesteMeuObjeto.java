package br.com.fiapride.main;

import br.com.fiapride.pessoal.Tear;

/** Testes do projeto pessoal: controle de teares de uma tecelagem. */
public class TesteMeuObjeto {

    public static void main(String[] args) {
        System.out.println("=== TECELAGEM: Operação dos Teares ===");

        Tear tear1 = new Tear();
        tear1.setCodigo("TEAR-01");
        tear1.setModelo("AirMax 500");

        // tear1.metrosProduzidos = -50.0;   // não compila mais: o atributo é privado

        tear1.setModelo("");                 // recusado: modelo vazio
        tear1.ajustarVelocidade(800);
        tear1.ajustarVelocidade(5000);       // recusado: acima da faixa segura

        tear1.registrarProducao(120.5);
        tear1.registrarProducao(-10.0);      // recusado: produção negativa

        System.out.println("TEAR-01 atingiu a meta de 100 m? " + tear1.atingiuMeta(100.0));
        System.out.println(tear1.getCodigo() + " | Modelo: " + tear1.getModelo() + " | "
                + tear1.getVelocidadeRpm() + " rpm | Produção: " + tear1.getMetrosProduzidos() + " m");
    }
}
