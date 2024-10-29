package br.insper.grupo1.dto;

import br.insper.grupo1.model.Cidade;

public class HotelDTO {

    private String nome;
    private String endereco;
    private int capacidade;
    private double precoPorDiaria;
    private String cidadeId;

    public HotelDTO() {}

    public HotelDTO(String nome, String endereco, int capacidade, double precoPorDiaria, String cidadeId) {
        this.nome = nome;
        this.endereco = endereco;
        this.capacidade = capacidade;
        this.precoPorDiaria = precoPorDiaria;
        this.cidadeId = cidadeId;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public double getPrecoPorDiaria() { return precoPorDiaria; }
    public void setPrecoPorDiaria(double precoPorDiaria) { this.precoPorDiaria = precoPorDiaria; }

    public String getCidadeId() { return cidadeId; }
    public void setCidadeId(String cidadeId) { this.cidadeId = cidadeId; }
}
