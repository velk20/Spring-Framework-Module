package bg.softuni.errors.web;

import bg.softuni.errors.model.ApiErrorDTO;
import bg.softuni.errors.model.ObjectNotFoundException;
import bg.softuni.errors.model.ProductDTO;
import bg.softuni.errors.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductByID(@PathVariable("id") Long id) {
        ProductDTO productById = getProductById(id);

        if (productById == null) {
            throw new ProductNotFoundException(id);
        }
        return ResponseEntity.ok(productById);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    public @ResponseBody ApiErrorDTO handleRestErrors(ProductNotFoundException e) {
        return new ApiErrorDTO(e.getId(), "Product was not dound");
    }


    private ProductDTO getProductById(Long id) {

        return null;
    }
}
