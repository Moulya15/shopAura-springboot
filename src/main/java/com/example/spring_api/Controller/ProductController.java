package com.example.spring_api.Controller;

import com.example.spring_api.Entity.ProductEntity;
import com.example.spring_api.Entity.User;
import com.example.spring_api.Repo.ProductRepo;
;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductRepo prodRepo;

//    @PostMapping("/ProductRegistration")
//    public ResponseEntity<?> userRegistration(@RequestBody ProductEntity prod){
//        prodRepo.save(prod);
//        return new ResponseEntity<>("product registration is successful", HttpStatus.OK);
//    }
//baseurl instead path
 //to save the image with other fields like product-name,price ect, we need this type of postMapping
    @PostMapping("/ProductRegistration")
    public ResponseEntity<?> addProduct(@RequestParam("name") String name,
                                        @RequestParam("price") Double price,
                                        @RequestParam("description") String description,
                                        @RequestParam("image") MultipartFile image){
        try{

            String fileName=System.currentTimeMillis()+"-"+ image.getOriginalFilename();

            Path path = Paths.get("uploads/"+fileName);
            Files.createDirectories(path.getParent());
            Files.write(path,image.getBytes());

            ProductEntity product=new ProductEntity();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImage(fileName);

            prodRepo.save(product);

            return ResponseEntity.ok("Product saved");
        }
        catch(Exception e){
            return ResponseEntity.status(500).body("Error saving product");
        }
    }


  //to upload image via multipart
    @PostMapping("/imageUpload")
    public String uploadFile(@RequestParam ("file")MultipartFile file){
        try{
            String filename=file.getOriginalFilename();
            byte[] bytes=file.getBytes();

            //save file example
            Path path = Paths.get("imageUpload/"+ filename);
            Files.write(path,bytes);

            return "Uploaded successfully";
        }
        catch (Exception e){
            return "Upload failed";
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        return new ResponseEntity<>(prodRepo.findAll(),HttpStatus.OK);
    }

    @GetMapping("/GetImage/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable String fileName) throws Exception{
        Path path = Paths.get("uploads/"+fileName);
        var resource = new UrlResource(path.toUri());

        return ResponseEntity.ok().body(resource);
    }

    //to fetch individual products when you open it
    @GetMapping("/getProductsByID/{id}")
    public ResponseEntity<?> getProductsById(@PathVariable Integer id){
        var checkProduct=prodRepo.findById(id).orElseThrow(()->new RuntimeException("Product not found"));
        return new ResponseEntity<>(checkProduct,HttpStatus.OK);
    }
}
