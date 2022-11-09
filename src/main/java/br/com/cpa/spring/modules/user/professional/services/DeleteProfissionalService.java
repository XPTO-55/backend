package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class DeleteProfissionalService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private ProfissionalRepository profissionalRepository;

    public void execute(Long id) {
        profissionalRepository.deleteById(id);
    }
}
