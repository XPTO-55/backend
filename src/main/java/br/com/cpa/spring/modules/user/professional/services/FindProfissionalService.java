package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.models.Patient;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class FindProfissionalService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    public ProfissionalRepository profissionalRepository;

    public List<Profissional> execute() {

        // utilizando as funçoes do repository para buscar todos os usuários
        List<Profissional> professional = profissionalRepository.findAllAndRatings();

        return professional;
    }
}
