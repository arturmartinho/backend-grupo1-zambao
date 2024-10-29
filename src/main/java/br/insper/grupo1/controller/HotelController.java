package br.insper.grupo1.controller;

import br.insper.grupo1.dto.HotelDTO;
import br.insper.grupo1.model.Hotel;
import br.insper.grupo1.model.Cidade;
import br.insper.grupo1.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO hotelDTO) {
        Cidade cidade = /* Obtenha a cidade usando hotelDTO.getCidadeId() */;
        Hotel hotel = new Hotel(
            hotelDTO.getNome(),
            hotelDTO.getEndereco(),
            hotelDTO.getCapacidade(),
            hotelDTO.getPrecoPorDiaria(),
            cidade
        );
        Hotel createdHotel = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String id, @RequestBody HotelDTO hotelDTO) {
        Cidade cidade = /* Obtenha a cidade usando hotelDTO.getCidadeId() */;
        Hotel hotel = new Hotel(
            hotelDTO.getNome(),
            hotelDTO.getEndereco(),
            hotelDTO.getCapacidade(),
            hotelDTO.getPrecoPorDiaria(),
            cidade
        );
        hotel.setId(id);
        Hotel updatedHotel = hotelService.saveHotel(hotel);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        Hotel hotel = hotelService.getHotelById(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping("/cidade")
    public ResponseEntity<List<Hotel>> getHotelsByCidade(@RequestParam Cidade cidade) {
        List<Hotel> hotels = hotelService.getHotelsByCidade(cidade);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/nome")
    public ResponseEntity<List<Hotel>> getHotelsByNome(@RequestParam String nome) {
        List<Hotel> hotels = hotelService.getHotelsByNome(nome);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/endereco")
    public ResponseEntity<List<Hotel>> getHotelsByEndereco(@RequestParam String endereco) {
        List<Hotel> hotels = hotelService.getHotelsByEndereco(endereco);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/preco")
    public ResponseEntity<List<Hotel>> getHotelsByPrecoMaximo(@RequestParam double precoMaximo) {
        List<Hotel> hotels = hotelService.getHotelsByPrecoMaximo(precoMaximo);
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable String id) {
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}