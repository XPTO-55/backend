package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.modules.user.professional.dtos.UpdateProfissionalDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class UpdateProfissionalService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private ProfissionalRepository patientRepository;

    public Profissional execute(Long id, @NotNull UpdateProfissionalDTO updateProfissionalDTO) {
        // Criando um novo paciente com os argumentos que foram passamos para  afunção
        Profissional patient = new Profissional();
        patient.setId(id);
        patient.setName(updateProfissionalDTO.getName());
        patient.setEmail(updateProfissionalDTO.getEmail());
        patient.setPassword(updateProfissionalDTO.getPassword());
        // Atualizando o paciente no banco de dados com a função do repository
        // Note que no JPA, a função save também atualiza, mas precisamos passar o id para essa função para caso ele encontre no banco ele atualize
        return patientRepository.save(patient);
    }
}
