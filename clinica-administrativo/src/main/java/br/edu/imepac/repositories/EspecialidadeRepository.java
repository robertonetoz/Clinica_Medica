package br.edu.imepac.repositories;


import br.edu.imepac.models.EspecialidadeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface EspecialidadeRepository extends JpaRepository<EspecialidadeModel, Long> {

    }