package br.insper.grupo1.repository;

import br.insper.grupo1.model.Hotel;
import br.insper.grupo1.model.Cidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel, String> {

    // Consulta por cidade
    List<Hotel> findByCidade(Cidade cidade);

    // Consulta por nome
    List<Hotel> findByNomeContainingIgnoreCase(String nome);

    // Consulta por endereço
    List<Hotel> findByEnderecoContainingIgnoreCase(String endereco);

    // Consulta por faixa de preço
    List<Hotel> findByPrecoPorDiariaLessThanEqual(double precoMaximo);
}