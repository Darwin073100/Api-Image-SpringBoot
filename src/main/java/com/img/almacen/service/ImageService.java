package com.img.almacen.service;

import com.img.almacen.repository.ImageRepsoitory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ImageService implements ImageRepsoitory {

    private final String PATH_NUBE = "NUBE/IMAGENES/";

    /**
     * @param idImg
     * @param image
     * @return
     */
    @Override
    public byte[] upLoadImg(int idImg, MultipartFile image){
        LocalDate fecha = LocalDate.now();
        String fileName = idImg + "-IMG-"+ fecha +"."+ image.getContentType().split("/")[1];
        try {
            Files.copy(image.getInputStream(), Paths.get(PATH_NUBE + fileName), StandardCopyOption.REPLACE_EXISTING);
            byte[] bytes = image.getBytes();
            Path path = Paths.get(PATH_NUBE+fileName);
            Files.write(path, bytes);
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public byte[] getImg(String name){
            try {
                Path path = Paths.get(PATH_NUBE+name);
                byte[] image = Files.readAllBytes(path);
                return image;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public boolean deleteImg(String name) {
        File file = new File(PATH_NUBE + name);
        if (file.exists()){
            file.delete();
            return true;
        }else {
            return  false;
        }
    }
}
