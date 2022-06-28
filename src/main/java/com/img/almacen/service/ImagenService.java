package com.img.almacen.service;

import com.img.almacen.repository.ImagenRepsoitory;
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

@Service
public class ImagenService implements ImagenRepsoitory {

    private final String PATH_NUBE = "NUBE/IMAGENES/";

    /**
     * @param name
     * @return
     */
    @Override
    public boolean exist(String name) {
        File file = new File(name);
        if (file.exists()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @param idImg
     * @param image
     * @return
     */
    @Override
    public boolean upLoadImg(int idImg, MultipartFile image){
        LocalDate fecha = LocalDate.now();
        String fileName = idImg + "-IMG-"+ fecha + image.getContentType().split("/")[1];
        try {
            Files.copy(image.getInputStream(), Paths.get(PATH_NUBE + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (exist(fileName)) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public byte[] getImg(String name){
        if (exist(name)){
            File file = new File(name);
            try {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes, 0, inputStream.available());
                return bytes;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            return null;
        }
    }

    /**
     * @param name
     * @return
     */
    @Override
    public boolean deleteImg(String name) {
        if (exist(name)){
            File file = new File(name);
            file.delete();
            return true;
        }else {
            return  false;
        }
    }
}
