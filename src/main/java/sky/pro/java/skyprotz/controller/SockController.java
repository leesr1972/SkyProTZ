package sky.pro.java.skyprotz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sky.pro.java.skyprotz.model.Sock;
import sky.pro.java.skyprotz.service.SockService;

@RestController
@RequestMapping("/api/socks")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @Operation(
            summary = "Добавление требуемого количества пар носков.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/income")
    public ResponseEntity incomeSocks(@RequestBody Sock sock,
                                      @RequestParam int quantity) {
        sockService.incomeSocks(sock, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Удаление требуемого количества пар носков.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping("/outcome")
    public ResponseEntity outcomeSocks(@RequestBody Sock sock,
                                       @RequestParam int quantity) {
        sockService.outcomeSocks(sock, quantity);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Получение количества пар носков по требуемым параметрам.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping
    public ResponseEntity<Integer> getQuantityOfSocks (@RequestParam String color,
                                                       @RequestParam String operation,
                                                       @RequestParam int cottonPart) {
        Integer result = null;
        if (operation.equals("moreThan")) {
            result = sockService.getSocksWithCottonPartMoreThan(color, cottonPart);
        }
        if (operation.equals("lessThan")) {
            result = sockService.getSockWithCottonPartLessThan(color, cottonPart);
        }
        if (operation.equals("equal")) {
            result = sockService.getSockWithCottonPartEqual(color, cottonPart);
        }
        return ResponseEntity.ok(result);
    }

}
