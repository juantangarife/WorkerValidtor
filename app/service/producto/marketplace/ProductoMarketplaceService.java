package service.producto.marketplace;

import com.google.inject.ImplementedBy;
import model.marketplace.ProductoMarketplace;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(ProductoMarketplaceServiceImpl.class)
public interface ProductoMarketplaceService {

    public CompletionStage<List<ProductoMarketplace>> getProductosMarketplace();

}