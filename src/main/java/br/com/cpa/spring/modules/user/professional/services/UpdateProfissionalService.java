package br.com.cpa.spring.modules.user.professional.services;

import br.com.cpa.spring.models.Address;
import br.com.cpa.spring.models.Profissional;
import br.com.cpa.spring.modules.user.professional.ProfissionalRepository;
import br.com.cpa.spring.modules.user.professional.dtos.UpdateProfissionalDTO;
import br.com.cpa.spring.repositories.AddressRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Diz que essa classe uma service do sistema (Camada que contém a regra de negócio do sistema)
@Service
public class UpdateProfissionalService {
    private BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Injeta(importa) o repository (Camada do sistema que manipula o banco de dados)
    @Autowired
    private ProfissionalRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    public Profissional execute(Long id, @NotNull UpdateProfissionalDTO updateProfissionalDTO) {
        // Criando um novo paciente com os argumentos que foram passamos para  afunção
        Profissional profissional = new Profissional();
        profissional.setName(updateProfissionalDTO.getName());
        profissional.setCpf(updateProfissionalDTO.getCpf());
        profissional.setAbout(updateProfissionalDTO.getAbout());
        profissional.setGraduacao(updateProfissionalDTO.getGraduacao());
        profissional.setEspecialidade(updateProfissionalDTO.getEspecialidade());
        profissional.setTelefoneFixo(updateProfissionalDTO.getAddressline());
        profissional.setTelefoneCelular(updateProfissionalDTO.getPhone());
        profissional.setDataDeNascimento(updateProfissionalDTO.getBirthday());
        profissional.setEmail(updateProfissionalDTO.getEmail());
        profissional.setPassword(passwordEncoder().encode(updateProfissionalDTO.getPassword()));

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
