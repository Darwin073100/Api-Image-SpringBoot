package com.img.almacen.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Repository
public interface ImagenRepsoitory {

    boolean exist(String name);
    boolean upLoadImg(int idImg, MultipartFile image);

    byte[] getImg(String name);

    boolean deleteImg(String name);
}
