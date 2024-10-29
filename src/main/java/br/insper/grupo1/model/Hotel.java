package br.insper.grupo1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "hotels")
public class Hotel {

    @Id
    private String id;

    private String nome;
    private String endereco;
    private int capacidade;
    private double precoPorDiaria;

    @DBRef
    private Cidade cidade;

    public Hotel() {}

    public Hotel(String nome, String endereco, int capacidade, double precoPorDiaria, Cidade cidade) {
        this.nome = nome;
        this.endereco = endereco;
        this.capacidade = capacidade;
        this.precoPorDiaria = precoPorDiaria;
        this.cidade = cidade;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }

    public double getPrecoPorDiaria() { return precoPorDiaria; }
    public void setPrecoPorDiaria(double precoPorDiaria) { this.precoPorDiaria = precoPorDiaria; }

    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }
}