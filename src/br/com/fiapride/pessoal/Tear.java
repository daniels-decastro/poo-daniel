package br.com.fiapride.pessoal;

/**
 * Tear: máquina da tecelagem que cruza os fios de trama com os fios de urdume.
 * Atributos privados: velocidade e produção só mudam pelos métodos de operação.
 * Construtores: todo tear nasce com código, modelo e uma velocidade válida.
 * Abstrata: na fábrica não existe "tear genérico", só teares a jato de ar, de pinças etc.
 * Manutenivel: todo tear assina o contrato de manutenção preventiva, e as filhas herdam esse contrato.
 */
public abstract class Tear implements Manutenivel {

    // Faixa segura de operação. Constantes evitam "números mágicos" espalhados pelo código.
    private static final int VELOCIDADE_MINIMA_RPM = 100;
    private static final int VELOCIDADE_MAXIMA_RPM = 1200;
    private static final int VELOCIDADE_PADRAO_RPM = 450;

    // Tecido de referência para estimar produção: 20 batidas por centímetro = 2000 por metro.
    private static final int BATIDAS_POR_METRO = 2000;
    private static final int MINUTOS_POR_HORA = 60;

    private String codigo;
    private String modelo;
    private int velocidadeRpm;          // batidas (inserções de trama) por minuto
    private double metrosProduzidos;
    private int horasDesdeUltimaManutencao;

    // O construtor padrão Tear() existiu até a Aula 7. Com a classe abstrata ele perdeu o sentido:
    // um "tear sem tipo" não pode mais ser criado, então foi removido.

    /** Cadastro comum: o tear começa na velocidade padrão. Chamado pelas filhas via super(). */
    public Tear(String codigo, String modelo) {
        this(codigo, modelo, VELOCIDADE_PADRAO_RPM);
    }

    /** Construtor completo: é aqui que todas as regras de nascimento são aplicadas. */
    public Tear(String codigo, String modelo, int velocidadeRpm) {
        this.setCodigo(codigo);
        if (modelo == null || modelo.trim().isEmpty()) {
            System.out.println("Erro de validação: o modelo do tear não pode ser vazio.");
        } else {
            this.modelo = modelo;
        }
        this.setVelocidadeRpm(velocidadeRpm);
        this.setMetrosProduzidos(0.0);
    }

    /** Recadastro de patrimônio: troca o código da máquina, como a troca de placa no Detran. */
    public void recadastrarCodigo(String novoCodigo) {
        System.out.println("Recadastrando " + this.codigo + " no patrimônio como \"" + novoCodigo + "\"...");
        this.setCodigo(novoCodigo);
    }

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

    public void registrarHorasDeUso(int horas) {
        if (horas <= 0) {
            System.out.println("Erro: as horas de uso devem ser maiores que zero.");
            return;
        }
        this.horasDesdeUltimaManutencao = this.horasDesdeUltimaManutencao + horas;
    }

    /** Implementação do contrato Manutenivel, usando a constante definida na interface. */
    @Override
    public boolean precisaDeManutencao() {
        return this.horasDesdeUltimaManutencao >= Manutenivel.INTERVALO_MANUTENCAO_HORAS;
    }

    @Override
    public void realizarManutencao() {
        System.out.println("Manutenção do " + this.codigo + ": revisão do batente, troca de peças de desgaste e lubrificação ("
                + this.horasDesdeUltimaManutencao + " h de uso).");
        this.horasDesdeUltimaManutencao = 0;
    }

    /**
     * Contrato da produção. Era um método com resposta genérica até a Aula 7. Com a classe abstrata,
     * essa resposta nunca seria usada, então virou abstrato: cada tipo de tear é obrigado a calcular
     * a produção com a sua eficiência real.
     */
    public abstract String calcularProducaoPorHora();

    /** Cada tipo de tear é obrigado a informar como a trama atravessa o tecido. */
    public abstract void exibirTipoDeInsercao();

    /** Converte a velocidade em metros por hora. Protegido: só as filhas usam, no cálculo da produção. */
    protected double estimarMetrosPorHora(double eficiencia) {
        return this.velocidadeRpm * MINUTOS_POR_HORA * eficiencia / BATIDAS_POR_METRO;
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

    public int getHorasDesdeUltimaManutencao() {
        return this.horasDesdeUltimaManutencao;
    }

    // Não existe setModelo(): o modelo de uma máquina não muda depois de fabricada.

    /** Agora privado: o código só muda pelo processo de recadastrarCodigo(). */
    private void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Erro de validação: o código do tear não pode ser vazio.");
            return;
        }
        this.codigo = codigo;
    }

    /** Privado: a velocidade só muda pelo construtor e por ajustarVelocidade(), dentro da faixa segura. */
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
