package edu.javeriana.ingenieria.social.calendario.controlador;

import edu.javeriana.ingenieria.social.calendario.dominio.Calendario;
import edu.javeriana.ingenieria.social.calendario.servicio.ServicioCalendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/calendarios")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorCalendario {

    @Autowired
    private ServicioCalendario servicio;

    @GetMapping("listar")
    public List<Calendario> obtenerCalendarios(){
        return servicio.obtenerCalendarios();
    }

    @GetMapping("obtener")
    public ResponseEntity<List<Calendario>> obtenerCalendario(@RequestParam Date fecha){
        if(fecha==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerCalendariosPorFecha(fecha).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(servicio.obtenerCalendariosPorFecha(fecha), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Calendario> crearCalendario(@RequestBody Calendario calendario){
        if(calendario==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.calendarioExiste(calendario.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearCalendario(calendario), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Calendario> actualizarCalendario(@RequestParam Integer id, @RequestBody Calendario calendario){
        if(id == null || calendario == null || !Objects.equals(id, calendario.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarCalendario(id, calendario)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(calendario, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarCalendario(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarCalendario(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
