package edu.javeriana.ingenieria.social.calendario.repositorio;

import edu.javeriana.ingenieria.social.calendario.dominio.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RepositorioCalendario extends JpaRepository<Calendario, Integer> {
    List<Calendario> findAllByFecha(Date fecha);
}
