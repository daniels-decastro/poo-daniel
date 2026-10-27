package br.com.fiapride.model;

/**
 * Veículo da frota do FiapRide.
 * O construtor obriga todo veículo a nascer com placa e modelo.
 */
public class Veiculo {

    private String placa;
    private String modelo;

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

    /** Representa o processo real de troca de placa no Detran, e não uma simples edição de dado. */
    public void atualizarPlaca(String novaPlaca) {
        System.out.println("Solicitada atualização de placa no Detran para o veículo " + this.modelo + "...");
        this.setPlaca(novaPlaca);
    }

    // Não existe setModelo(): um carro não muda de modelo depois de fabricado.

    private void setPlaca(String novaPlaca) {
        if (novaPlaca == null || novaPlaca.trim().isEmpty()) {
            System.out.println("Erro de validação: a placa informada é inválida.");
            return;
        }
        this.placa = novaPlaca;
    }
}
