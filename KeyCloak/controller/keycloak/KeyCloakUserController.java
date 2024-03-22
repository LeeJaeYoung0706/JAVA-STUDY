package com.keti.iam.idthub.controller.keycloak;

import com.keti.iam.idthub.service.keycloak.KeyCloakService;
import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.response.Response;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keycloak")
@RequiredArgsConstructor
public class KeyCloakUserController {

    private final KeyCloakService keyCloakService;


    @PostMapping
    @RequestMapping("/getSecret")
    public Response<String> keyCloakGetSecret() throws RestException {
        return Response.<String>builder("OK" , 200)
                .data(keyCloakService.keyCloakClientGetSecret())
                .build();
    }

    @GetMapping
    @RequestMapping("/info")
    public Response<String>testKeyCloakUserInfo(Authorization authorization){
        return Response.<String>builder("OK" , 200)
                .build();
    }
}
