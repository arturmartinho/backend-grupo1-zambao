package br.insper.grupo1.service;

import br.insper.grupo1.model.Hotel;
import br.insper.grupo1.model.Cidade;
import br.insper.grupo1.repository.HotelRepository;
import br.insper.grupo1.exception.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(String id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel com ID " + id + " não encontrado"));
    }

    public void deleteHotelById(String id) throws HotelNotFoundException {
        if (!hotelRepository.existsById(id)) {
            throw new HotelNotFoundException("Hotel com ID " + id + " não encontrado");
        }
        hotelRepository.deleteById(id);
    }

    public List<Hotel> getHotelsByCidade(Cidade cidade) {
        return hotelRepository.findByCidade(cidade);
    }

    public List<Hotel> getHotelsByNome(String nome) {
        return hotelRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Hotel> getHotelsByEndereco(String endereco) {
        return hotelRepository.findByEnderecoContainingIgnoreCase(endereco);
    }

    public List<Hotel> getHotelsByPrecoMaximo(double precoMaximo) {
        return hotelRepository.findByPrecoPorDiariaLessThanEqual(precoMaximo);
    }
}