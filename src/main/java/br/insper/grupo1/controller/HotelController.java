package br.insper.grupo1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.insper.grupo1.dto.HotelDTO;
import br.insper.grupo1.model.Cidade;
import br.insper.grupo1.model.Hotel;
import br.insper.grupo1.service.CidadeService;
import br.insper.grupo1.service.HotelService;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:3000")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CidadeService cidadeService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO hotelDTO) {
        Cidade cidade = cidadeService.getCidadeById(hotelDTO.getCidadeId());
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
        Cidade cidade = cidadeService.getCidadeById(hotelDTO.getCidadeId());
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

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id) {
        Hotel hotel = hotelService.getHotelById(id);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels(
            @RequestParam Optional<String> cidadeId,
            @RequestParam Optional<String> nome,
            @RequestParam Optional<String> endereco,
            @RequestParam Optional<Double> precoMaximo) {

        List<Hotel> hotels;

        if (cidadeId.isPresent()) {
            Cidade cidade = cidadeService.getCidadeById(cidadeId.get());
            hotels = hotelService.getHotelsByCidade(cidade);
        } else if (nome.isPresent()) {
            hotels = hotelService.getHotelsByNome(nome.get());
        } else if (endereco.isPresent()) {
            hotels = hotelService.getHotelsByEndereco(endereco.get());
        } else if (precoMaximo.isPresent()) {
            hotels = hotelService.getHotelsByPrecoMaximo(precoMaximo.get());
        } else {
            hotels = hotelService.getAllHotels();
        }

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable String id) {
        hotelService.deleteHotelById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}