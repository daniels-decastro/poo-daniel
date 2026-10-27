package br.com.fiapride.main;

import br.com.fiapride.pessoal.Tear;

/** Testes do projeto pessoal: controle de teares de uma tecelagem. */
public class TesteMeuObjeto {

    public static void main(String[] args) {
        System.out.println("=== TECELAGEM: Cadastro de Teares ===");

        Tear tear1 = new Tear();
        tear1.codigo = "TEAR-01";
        tear1.modelo = "AirMax 500";
        tear1.velocidadeRpm = 800;
        tear1.metrosProduzidos = 0.0;

        Tear tear2 = new Tear();
        tear2.codigo = "TEAR-02";
        tear2.modelo = "Rapier 220";
        tear2.velocidadeRpm = 450;
        tear2.metrosProduzidos = 0.0;

        System.out.println(tear1.codigo + " | Modelo: " + tear1.modelo + " | " + tear1.velocidadeRpm + " rpm");
        System.out.println(tear2.codigo + " | Modelo: " + tear2.modelo + " | " + tear2.velocidadeRpm + " rpm");
    }
}
