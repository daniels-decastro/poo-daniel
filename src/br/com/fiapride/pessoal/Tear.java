package br.com.fiapride.pessoal;

/**
 * Tear: máquina da tecelagem que cruza os fios de trama com os fios de urdume.
 * Abstração: guardamos só o que o controle de produção usa (nada de cor da máquina ou fabricante do motor).
 */
public class Tear {

    public String codigo;
    public String modelo;
    public int velocidadeRpm;          // batidas (inserções de trama) por minuto
    public double metrosProduzidos;
}
