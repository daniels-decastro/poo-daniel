package br.com.fiapride.model;

/**
 * Veículo da frota do FiapRide.
 * Abstrata: não existe "veículo genérico" no mundo real, só carros e motos.
 * Ela é o molde que guarda o que é comum a todos e não pode ser instanciada com new.
 */
public abstract class Veiculo {

    private String placa;
    private String modelo;
    private double nivelCombustivelEmLitros;

    /** Classe abstrata também tem construtor: ele é chamado pelas filhas via super(). */
    public Veiculo(String placa, String modelo) {
        this.setPlaca(placa);
        this.modelo = modelo;
    }

    public String getPlaca() {
        return this.placa;
    }

    public String getModelo() {
        return this.modelo;
    }

    public double getNivelCombustivelEmLitros() {
        return this.nivelCombustivelEmLitros;
    }

    /** Representa o processo real de troca de placa no Detran, e não uma simples edição de dado. */
    public void atualizarPlaca(String novaPlaca) {
        System.out.println("Solicitada atualização de placa no Detran para o veículo " + this.modelo + "...");
        this.setPlaca(novaPlaca);
    }

    public void abastecer(double litros) {
        if (litros <= 0) {
            System.out.println("Erro: a quantidade abastecida deve ser maior que zero.");
            return;
        }
        this.setNivelCombustivelEmLitros(this.nivelCombustivelEmLitros + litros);
    }

    /**
     * Contrato da autonomia. A resposta da mãe é genérica, porque cada tipo de veículo
     * tem um consumo diferente. As filhas sobrescrevem com @Override.
     */
    public String calcularAutonomia() {
        return "Autonomia não definida para um veículo genérico.";
    }

    /**
     * Método abstrato: não tem corpo. Cada filha concreta é obrigada a dizer que tipo de veículo ela é,
     * e o compilador recusa uma filha que esqueça de implementar.
     */
    public abstract void exibirTipo();

    // Não existe setModelo(): um carro não muda de modelo depois de fabricado.

    private void setPlaca(String novaPlaca) {
        if (novaPlaca == null || novaPlaca.trim().isEmpty()) {
            System.out.println("Erro de validação: a placa informada é inválida.");
            return;
        }
        this.placa = novaPlaca;
    }

    private void setNivelCombustivelEmLitros(double litros) {
        if (litros < 0) {
            System.out.println("Erro de segurança: nível de combustível negativo bloqueado.");
            return;
        }
        this.nivelCombustivelEmLitros = litros;
    }
}
