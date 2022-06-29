package com.img.almacen.controller;

import com.img.almacen.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

@RestController
@RequestMapping("image/")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name){
        byte[] bytes = imageService.getImg(name);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }

    @PostMapping("/up")
    public ResponseEntity<byte[]> upLoadImagen(@RequestParam("Id") int Id, @RequestParam("file") MultipartFile file) {
        byte[] bytes = imageService.upLoadImg(Id, file);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
    }

    @DeleteMapping("delete/{name}")
    public ResponseEntity delete(@PathVariable String name){
        if (imageService.deleteImg(name)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return  new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
