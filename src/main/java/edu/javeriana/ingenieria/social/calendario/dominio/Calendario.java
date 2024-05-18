package edu.javeriana.ingenieria.social.calendario.dominio;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Getter
@Setter
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    @Column(name="hora_inicio")
    private Time inicio;
    @Column(name="hora_final")
    private Time fin;
}
