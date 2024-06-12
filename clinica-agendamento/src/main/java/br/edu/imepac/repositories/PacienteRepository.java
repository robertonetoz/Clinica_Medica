package br.edu.imepac.repositories;


import br.edu.imepac.models.PacienteModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModels, Long>{
}
