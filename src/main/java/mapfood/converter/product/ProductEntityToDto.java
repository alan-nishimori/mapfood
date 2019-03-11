package mapfood.converter.product;

import mapfood.dto.establishment.product.ProductDto;
import mapfood.model.establishment.product.Product;

public class ProductEntityToDto {

    private Product product;

    public ProductEntityToDto(final Product product) {
        this.product = product;
    }

    public ProductDto build() {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setClassification(product.getClassification());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
