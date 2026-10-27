package br.com.fiapride.model;

/**
 * Celular do motorista: NÃO é um Veiculo (está fora da hierarquia), mas também é Recarregavel.
 * A interface permite tratar objetos de famílias diferentes pelo mesmo contrato.
 */
public class Celular implements Recarregavel {

    private String marca;
    private int nivelBateria;   // de 0 a 100 %

    public Celular(String marca) {
        this.setMarca(marca);
        this.nivelBateria = Recarregavel.CARGA_MINIMA;
    }

    @Override
    public void recarregar(int percentual) {
        if (percentual < Recarregavel.CARGA_MINIMA || percentual > Recarregavel.CARGA_MAXIMA) {
            System.out.println("Erro: percentual de carga inválido (" + percentual + "%).");
            return;
        }
        this.nivelBateria = percentual;
        System.out.println("Celular " + this.marca + " carregado para " + percentual + "%");
    }

    public String getMarca() {
        return this.marca;
    }

    public int getNivelBateria() {
        return this.nivelBateria;
    }

    private void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            System.out.println("Erro de validação: a marca do celular não pode ser vazia.");
            return;
        }
        this.marca = marca;
    }
}
