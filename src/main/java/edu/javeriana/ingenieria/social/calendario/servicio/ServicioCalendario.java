package edu.javeriana.ingenieria.social.calendario.servicio;

import edu.javeriana.ingenieria.social.calendario.dominio.Calendario;
import edu.javeriana.ingenieria.social.calendario.repositorio.RepositorioCalendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ServicioCalendario {
    @Autowired
    private RepositorioCalendario repositorio;

    public List<Calendario> obtenerCalendarios(){
        return repositorio.findAll();
    }

    public List<Calendario> obtenerCalendariosPorFecha(Date fecha){
        return repositorio.findAllByFecha(fecha);
    }

    public Calendario crearCalendario(Calendario calendario){
        return repositorio.save(calendario);
    }

    public Calendario actualizarCalendario(Integer id, Calendario calendario){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        calendario.setId(id);
        return repositorio.save(calendario);
    }

    public Calendario borrarCalendario(Integer id){
        Calendario aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean calendarioExiste(Integer id){
        return repositorio.existsById(id);
    }
}
