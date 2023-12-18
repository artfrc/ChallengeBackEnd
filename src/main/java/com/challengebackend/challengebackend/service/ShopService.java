package com.challengebackend.challengebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dependenceapi.domain.Product;
import com.dependenceapi.domain.User;
import com.challengebackend.challengebackend.dto.PurchasedDTO;
import com.challengebackend.challengebackend.dto.UpdateProductDTO;
import com.challengebackend.challengebackend.dto.UpdateUserDTO;
import com.challengebackend.challengebackend.infra.exceptions.ValidationExternalService;
import com.challengebackend.challengebackend.infra.exceptions.ValidationUpdateUserException;

@Service
public class ShopService {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ExternalServiceCaller externalServiceCaller;

    public void buyProduct(PurchasedDTO purchasedDTO) throws Exception {

        User user = userService.getUser(purchasedDTO.user_fk());

        if(!user.getActive()) {
            throw new ValidationUpdateUserException("Inactive user!");
        }

        String resultExternalService;
        
        try {
            resultExternalService = externalServiceCaller.callService(user.getName());
            
            if(resultExternalService.equalsIgnoreCase("negado")) {
                throw new ValidationExternalService("Denied service!");
            }

        } catch(ValidationExternalService e) {
            throw new ValidationExternalService(e.getMessage());
        } catch(Exception e) {
            throw new ValidationExternalService("Error processing order!");
        }

        Product product = productService.getProduct(purchasedDTO.product_fk());

        UpdateUserDTO updateUserDTO = new UpdateUserDTO(user.getId(), -product.getPrice(), null, product);
        
        userService.updateUser(updateUserDTO);

        UpdateProductDTO updateProductDTO = new UpdateProductDTO(product.getId(), null,user);

        productService.updateProduct(updateProductDTO);

        System.out.println(">> BUY PRODUCT HERE!");

    }

}
