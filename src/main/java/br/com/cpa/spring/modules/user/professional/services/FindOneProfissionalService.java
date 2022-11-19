package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class FindOneProfissionalService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    public ProfissionalRepository professionalRepository;

    public Profissional execute(Long id) {

        // utilizando as funçoes do repositoru para buscar todos os usuários
        Optional<Profissional> professionalExists = professionalRepository.findById(id);

        if (professionalExists.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profissional não encontrado");
        }

        return professionalExists.get();

    }
}
