package com.example.nbp_api.RestController;

import com.example.nbp_api.Service.NbpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

@RestController
public class NbpRestController {
    private final NbpService nbpService;

    public NbpRestController(NbpService nbpService) {
        this.nbpService = nbpService;
    }

    @Operation(summary = "Allows to get average value of chosen currency from table A by provided amount of days. If 'days' are not specified, ratio will be shown from last day. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Provided average exchange rate",
                    content = @Content(schema = @Schema(type = "number", format = "float"))),
            @ApiResponse(responseCode = "400", description = "Invalid request // Time limit exceeded",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @GetMapping("/{currency}")
    ResponseEntity<Float> averageExchangeRate(
            @PathVariable String currency,
            @RequestParam(required = false, defaultValue = "1") int days) {


        return ResponseEntity.ok(nbpService.averageExchangeRate(currency, days));
    }
}
