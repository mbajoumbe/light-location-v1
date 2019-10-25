package co.simplon.web;


import co.simplon.dao.HotelRepository;
import co.simplon.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class CatalogueRestController {
    private HotelRepository hotelRepository;
    public CatalogueRestController(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }
    @GetMapping(path="/photoHotel/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Hotel h = hotelRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Picture/Hotels/"+h.getPhotoName()));
    }
    @PostMapping(path = "/uploadPhoto/{id}")
    public void uploadPhoto(MultipartFile file, @PathVariable Long id) throws Exception{
        Hotel h = hotelRepository.findById(id).get();
        h.setPhotoName(id+".png");
        Files.write(Paths.get(System.getProperty("user.home")+"/Picture/hotels/"+h.getPhotoName()),file.getBytes());
        hotelRepository.save(h);
    }
}
