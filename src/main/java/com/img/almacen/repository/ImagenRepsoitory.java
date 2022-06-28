package com.img.almacen.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ImagenRepsoitory {

    boolean upLoadImg(int idImg, MultipartFile imagen);

    byte[] getImg(int idImg);

    boolean deleteImg(int idImg);
}
