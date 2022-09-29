package com.vincent.password_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vincent.password_manager.bean.SecretOption;
import com.vincent.password_manager.service.SecretService;
import com.vincent.password_manager.utils.ConstantType;
import com.vincent.password_manager.utils.ViewPackers;

@RestController
@RequestMapping("/secrets")
@PreAuthorize(ConstantType.HAS_ANY_ALL_AUTHORITY)
public class SecretController 
{
    @Autowired
    private SecretService secretService;
    
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String generateSecret(@RequestBody SecretOption secretOption)
    {
        return ViewPackers.toJson("secret", this.secretService.generateSecret(secretOption));
    }
}
