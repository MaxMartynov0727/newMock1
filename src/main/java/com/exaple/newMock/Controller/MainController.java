package com.exaple.newMock.Controller;

import com.exaple.newMock.Model.RequestDTO;
import com.exaple.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalance(@RequestBody RequestDTO requestDTO) {
        try {
            String clientId = requestDTO.getClientId();
            char firstDigit = clientId.charAt(0);
            BigDecimal maxLimit;

            if(firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
            } else {
                maxLimit = new BigDecimal(10000.00);
            }
            String RqUID = requestDTO.getRqUID();

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clientId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency("RUB");
            responseDTO.setBalance("900");
            responseDTO.setMaxLimit(maxLimit);

            log.error("****** RequestDTO ******" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("****** ResponseDTO ******" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
