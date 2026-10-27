package br.com.fiapride.pessoal;

/**
 * Tear: máquina da tecelagem que cruza os fios de trama com os fios de urdume.
 * Atributos privados: velocidade e produção só mudam pelos métodos de operação.
 */
public class Tear {

    // Faixa segura de operação. Constantes evitam "números mágicos" espalhados pelo código.
    private static final int VELOCIDADE_MINIMA_RPM = 100;
    private static final int VELOCIDADE_MAXIMA_RPM = 1200;

    private String codigo;
    private String modelo;
    private int velocidadeRpm;          // batidas (inserções de trama) por minuto
    private double metrosProduzidos;

    /** Ajusta a velocidade. A validação da faixa segura fica no setter. */
    public void ajustarVelocidade(int novaVelocidadeRpm) {
        System.out.println("Ajustando " + this.codigo + " para " + novaVelocidadeRpm + " rpm...");
        this.setVelocidadeRpm(novaVelocidadeRpm);
    }

    /** Soma ao contador o tecido produzido. Produção zero ou negativa não existe. */
    public void registrarProducao(double metros) {
        if (metros <= 0) {
            System.out.println("Erro: a produção registrada deve ser maior que zero.");
            return;
        }
        this.setMetrosProduzidos(this.metrosProduzidos + metros);
        System.out.println(this.codigo + " registrou " + metros + " m. Total: " + this.metrosProduzidos + " m.");
    }

    /** Compara a produção acumulada com a meta do turno. */
    public boolean atingiuMeta(double metaEmMetros) {
        if (metaEmMetros <= 0) {
            System.out.println("Erro: a meta deve ser maior que zero.");
            return false;
        }
        return this.metrosProduzidos >= metaEmMetros;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public int getVelocidadeRpm() {
        return this.velocidadeRpm;
    }

    public double getMetrosProduzidos() {
        return this.metrosProduzidos;
    }

    /** Público por enquanto: é a única forma de cadastrar o código enquanto não existe construtor. */
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Erro de validação: o código do tear não pode ser vazio.");
            return;
        }
        this.codigo = codigo;
    }

    /** Público por enquanto: é a única forma de cadastrar o modelo enquanto não existe construtor. */
    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            System.out.println("Erro de validação: o modelo do tear não pode ser vazio.");
            return;
        }
        this.modelo = modelo;
    }

    /** Privado: a velocidade só muda por ajustarVelocidade(), dentro da faixa segura. */
    private void setVelocidadeRpm(int velocidadeRpm) {
        if (velocidadeRpm < VELOCIDADE_MINIMA_RPM || velocidadeRpm > VELOCIDADE_MAXIMA_RPM) {
            System.out.println("Erro de validação: " + velocidadeRpm + " rpm está fora da faixa de "
                    + VELOCIDADE_MINIMA_RPM + " a " + VELOCIDADE_MAXIMA_RPM + " rpm.");
            return;
        }
        this.velocidadeRpm = velocidadeRpm;
    }

    /** Privado: a produção só cresce por registrarProducao(), nunca é editada à mão. */
    private void setMetrosProduzidos(double metrosProduzidos) {
        if (metrosProduzidos < 0) {
            System.out.println("Erro de segurança: produção negativa bloqueada.");
            return;
        }
        this.metrosProduzidos = metrosProduzidos;
    }
}
