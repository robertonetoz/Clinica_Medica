package br.edu.imepac.repositories;

import br.edu.imepac.models.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
    boolean existsByDataAndHora(String data, String hora);

    // Adicionando o método findByPacienteId
    List<ConsultaModel>
    findByPacienteId(Long pacienteId);

}