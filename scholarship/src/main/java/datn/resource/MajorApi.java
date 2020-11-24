package datn.resource;

import datn.entity.MajorEntity;
import datn.service.MajorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-public/major")
public class MajorApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(MajorApi.class);
    @Autowired
    private MajorService service;

    @GetMapping("/list-all")
    public ResponseEntity<List<MajorEntity>> getAll() {
        LOGGER.info("get all major");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/list-all-child")
    public ResponseEntity<List<MajorEntity>> getAllChild() {
        LOGGER.info("get all major");
        return ResponseEntity.ok(service.findAllChild());
    }

    @GetMapping("/list-major-by-parent")
    public ResponseEntity<List<MajorEntity>> getMajorByParent(@RequestParam(value = "parentId") Long parentId) {
        LOGGER.info("get all list-major-by-parent");
        return ResponseEntity.ok(service.findByParent(parentId));
    }

    @GetMapping("/update-parent")
    public ResponseEntity<MajorEntity> updateParent(@RequestParam(value = "majorId") Long majorId,
                                                          @RequestParam(value = "parentId") Long parentId) {
        LOGGER.info("get all update parent");
        return ResponseEntity.ok(service.updateParent(majorId,parentId));
    }
    @GetMapping("/delete-parent")
    public ResponseEntity<MajorEntity> deleteParent(@RequestParam(value = "majorId") Long majorId,
                                                    @RequestParam(value = "parentId") Long parentId) {
        LOGGER.info("get all update parent");
        return ResponseEntity.ok(service.deleteParent(majorId,parentId));
    }
    @GetMapping("/create")
    public ResponseEntity<MajorEntity> create(@RequestParam(value = "name") String name) {
        LOGGER.info("create");
        return ResponseEntity.ok(service.save(new MajorEntity(name)));
    }


}
