package br.com.fiapride.pessoal;

/**
 * Ordem de produção: uma quantidade de tecido a ser feita em um tear.
 * Associação (TEM-UM): a ordem tem um tear designado. Ela guarda o OBJETO Tear,
 * porque precisa registrar a produção na própria máquina.
 */
public class OrdemDeProducao {

    private String numero;
    private double metrosPlanejados;
    private Tear tearDesignado;
    private boolean concluida;

    public OrdemDeProducao(String numero, double metrosPlanejados, Tear tearDesignado) {
        this.setNumero(numero);
        this.setMetrosPlanejados(metrosPlanejados);
        this.setTearDesignado(tearDesignado);
        this.concluida = false;
    }

    /** Executa a ordem: a produção é somada no contador do próprio tear. Uma ordem não roda duas vezes. */
    public void executar() {
        if (this.concluida) {
            System.out.println("Ordem " + this.numero + " já foi concluída.");
            return;
        }
        System.out.println("Executando ordem " + this.numero + " no " + this.tearDesignado.getCodigo() + "...");
        this.tearDesignado.registrarProducao(this.metrosPlanejados);
        this.concluida = true;
    }

    public void exibirResumo() {
        System.out.println("Ordem " + this.numero
                + " | " + this.metrosPlanejados + " m"
                + " | Tear: " + this.tearDesignado.getCodigo() + " (" + this.tearDesignado.getModelo() + ")"
                + " | Situação: " + (this.concluida ? "concluída" : "pendente"));
    }

    public String getNumero() {
        return this.numero;
    }

    public double getMetrosPlanejados() {
        return this.metrosPlanejados;
    }

    public Tear getTearDesignado() {
        return this.tearDesignado;
    }

    public boolean isConcluida() {
        return this.concluida;
    }

    private void setNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            System.out.println("Erro de validação: o número da ordem não pode ser vazio.");
            return;
        }
        this.numero = numero;
    }

    private void setMetrosPlanejados(double metrosPlanejados) {
        if (metrosPlanejados <= 0) {
            System.out.println("Erro de validação: a ordem deve planejar mais de 0 m de tecido.");
            return;
        }
        this.metrosPlanejados = metrosPlanejados;
    }

    private void setTearDesignado(Tear tearDesignado) {
        if (tearDesignado == null) {
            System.out.println("Erro de validação: a ordem exige um tear.");
            return;
        }
        this.tearDesignado = tearDesignado;
    }
}
