package com.img.almacen.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Repository
public interface ImageRepsoitory {

    byte[] upLoadImg(int idImg, MultipartFile image);

    byte[] getImg(String name);

    boolean deleteImg(String name);
}
