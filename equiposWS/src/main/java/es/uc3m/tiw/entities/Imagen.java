package es.uc3m.tiw.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "imagenes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jugador_dni")
    private String jugadorDni;

    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;
}