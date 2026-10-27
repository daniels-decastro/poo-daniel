package br.com.fiapride.pessoal;

/**
 * Compressor de ar: abastece os teares a jato de ar. NÃO é um Tear (está fora da hierarquia),
 * mas também é Manutenivel, então entra no mesmo plano de manutenção das máquinas.
 */
public class Compressor implements Manutenivel {

    private String codigo;
    private int potenciaEmCv;
    private int horasDesdeUltimaManutencao;

    public Compressor(String codigo, int potenciaEmCv) {
        this.setCodigo(codigo);
        this.setPotenciaEmCv(potenciaEmCv);
        this.horasDesdeUltimaManutencao = 0;
    }

    public void registrarHorasDeUso(int horas) {
        if (horas <= 0) {
            System.out.println("Erro: as horas de uso devem ser maiores que zero.");
            return;
        }
        this.horasDesdeUltimaManutencao = this.horasDesdeUltimaManutencao + horas;
    }

    @Override
    public boolean precisaDeManutencao() {
        return this.horasDesdeUltimaManutencao >= Manutenivel.INTERVALO_MANUTENCAO_HORAS;
    }

    @Override
    public void realizarManutencao() {
        System.out.println("Manutenção do " + this.codigo + ": troca do óleo e do filtro de ar ("
                + this.horasDesdeUltimaManutencao + " h de uso).");
        this.horasDesdeUltimaManutencao = 0;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public int getPotenciaEmCv() {
        return this.potenciaEmCv;
    }

    public int getHorasDesdeUltimaManutencao() {
        return this.horasDesdeUltimaManutencao;
    }

    private void setCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            System.out.println("Erro de validação: o código do compressor não pode ser vazio.");
            return;
        }
        this.codigo = codigo;
    }

    private void setPotenciaEmCv(int potenciaEmCv) {
        if (potenciaEmCv <= 0) {
            System.out.println("Erro de validação: a potência do compressor deve ser maior que zero.");
            return;
        }
        this.potenciaEmCv = potenciaEmCv;
    }
}
