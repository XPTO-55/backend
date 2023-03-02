package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.modules.user.professional.dtos.UpdateProfissionalDTO;
import br.com.cpa.spring.repositories.AddressRepository;

import javax.transaction.Transactional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service

public class UpdateProfissionalService {
    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private ProfissionalRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional
    public Profissional execute(Long id, @NotNull UpdateProfissionalDTO updateProfissionalDTO) {
        if (!repository.existsById(id)) {
            new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        // Criando um novo paciente com os argumentos que foram passamos para  afunção
        Profissional profissional = repository.findById(id).get();
        profissional.setName(updateProfissionalDTO.getName());
        profissional.setCpf(updateProfissionalDTO.getCpf());
        profissional.setAbout(updateProfissionalDTO.getAbout());
        profissional.setIdentificacao(updateProfissionalDTO.getIdentificacao());
        profissional.setGraduacao(updateProfissionalDTO.getGraduacao());
        profissional.setEspecialidade(updateProfissionalDTO.getEspecialidade());
        profissional.setLandline(updateProfissionalDTO.getLandline());
        profissional.setPhone(updateProfissionalDTO.getPhone());
        profissional.setBirthday(updateProfissionalDTO.getBirthday());
        profissional.setEmail(updateProfissionalDTO.getEmail());

        if (updateProfissionalDTO.getAddress() != null) {
            Address address = new Address();
            address.setStreet(updateProfissionalDTO.getAddress().getStreet());
            address.setDistrict(updateProfissionalDTO.getAddress().getDistrict());
            address.setNumber(updateProfissionalDTO.getAddress().getNumber());
            address.setComplement(updateProfissionalDTO.getAddress().getComplement());
            address.setZipcode(updateProfissionalDTO.getAddress().getZipcode());
            address.setCity(updateProfissionalDTO.getAddress().getCity());
            address.setUf(updateProfissionalDTO.getAddress().getUf());
            addressRepository.save(address);
            profissional.setAddress(address);
        }

        // Atualizando o paciente no banco de dados com a função do repository
        // Note que no JPA, a função save também atualiza, mas precisamos passar o id para essa função para caso ele encontre no banco ele atualize
        return repository.save(profissional);
    }
}
