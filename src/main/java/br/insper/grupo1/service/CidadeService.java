package br.insper.grupo1.service;

import br.insper.grupo1.exception.CidadeNotFoundException;
import br.insper.grupo1.model.Cidade;
import br.insper.grupo1.model.Hotel;
import br.insper.grupo1.repository.CidadeRepository;
import br.insper.grupo1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private HotelRepository hotelRepository;

    public Cidade saveCidade(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public List<Cidade> getAllCidades() {
        return cidadeRepository.findAll();
    }

    public void deleteCidadeById(String id) {
        List<Hotel> hoteisNaCidade = hotelRepository.findByCidade(cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNotFoundException("Cidade com ID " + id + " não encontrada")));

        if (!hoteisNaCidade.isEmpty()) {
            throw new RuntimeException("Não é possível excluir cidade com hotéis cadastrados.");
        }
        cidadeRepository.deleteById(id);
    }
}
