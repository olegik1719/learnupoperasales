package com.github.olegik1719.learnup.operasales.lesson17.rest;

import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.OperaEntity;
import com.github.olegik1719.learnup.operasales.lesson17.services.DbSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    DbSalesService service;

    @Autowired
    public MainController(DbSalesService service) {
        this.service = service;
    }

    @GetMapping("/operas")
    public ResponseEntity<List<OperaEntity>> getOperaList() {
        try {
            List<OperaEntity> operas = service.getAllOpera();
            return ResponseEntity.ok(service.getAllOpera());
        } catch (Exception exception) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping("/admin/addopera")
    public ResponseEntity<OperaEntity> addOpera(@RequestBody Opera opera) {
        try {
            if (service.addOpera(opera.getName(), opera.getAuthor(), opera.getDescription(), opera.getCategory(), opera.getFullCapacity())) {
                return ResponseEntity.ok(service.getOpera(opera.getName(), opera.getAuthor()));
            }
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
