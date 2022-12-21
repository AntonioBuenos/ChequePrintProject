package by.smirnov.chequeprintproject.controller;

import by.smirnov.chequeprintproject.domain.ChequeRequest;
import by.smirnov.chequeprintproject.domain.ChequeResponse;
import by.smirnov.chequeprintproject.service.restservice.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cheque")
public class ChequeRestController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ChequeResponse> show(@RequestBody ChequeRequest request) {

        ChequeResponse response = productService.getCheque(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
