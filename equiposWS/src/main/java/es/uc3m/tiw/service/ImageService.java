package es.uc3m.tiw.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.uc3m.tiw.entities.Imagen;
import es.uc3m.tiw.repositories.ImageDataRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public String uploadImage(MultipartFile file, String dni) throws IOException {

        imageDataRepository.save(
            Imagen.builder()
                .jugadorDni(dni)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(file.getBytes())
            .build()
        );

        return file.getOriginalFilename();

    }

    @Transactional
    public byte[] getImage(String dni) {
        Optional<Imagen> dbImage = imageDataRepository.findByjugadorDni(dni);
        byte[] image = dbImage.get().getImageData();

        return image;
    }

}