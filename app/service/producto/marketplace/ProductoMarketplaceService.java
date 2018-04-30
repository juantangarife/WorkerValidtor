package service.producto.marketplace;

import com.google.inject.ImplementedBy;
import model.marketplace.ProductoMarketplace;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(ProductoMarketplaceServiceImpl.class)
public interface ProductoMarketplaceService {

    public CompletionStage<List<ProductoMarketplace>> getProductosMarketplace();
    public Boolean agregarProductoMarketplace(Long id, String descripcion, Double precio);
    public Long actualizarProductoMarketplace(Long id, String descripcion, Double precio);
    public Boolean eliminarProductoMarketplace(Long id);
}