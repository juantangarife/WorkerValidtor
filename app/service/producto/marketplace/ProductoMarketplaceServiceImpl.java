package service.producto.marketplace;

import dao.marketplace.ProductoMarketplaceDAO;
import model.marketplace.ProductoMarketplace;


import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ProductoMarketplaceServiceImpl implements ProductoMarketplaceService {

    private final ProductoMarketplaceDAO productoMarketplaceDAO;

    @Inject
    public ProductoMarketplaceServiceImpl(ProductoMarketplaceDAO productoMarketplaceDAO){
        this.productoMarketplaceDAO = productoMarketplaceDAO;
    }

    public CompletionStage<List<ProductoMarketplace>> getProductosMarketplace(){
        return productoMarketplaceDAO.getProductosMarketplace();
    }

    public Boolean agregarProductoMarketplace(Long id, String descripcion, Double precio){
        return productoMarketplaceDAO.agregarProductoMarketplace(id, descripcion, precio);
    }

    public Long actualizarProductoMarketplace(Long id, String descripcion, Double precio){
        return productoMarketplaceDAO.actualizarProductoMarketplace(id, descripcion, precio);
    }

    public Boolean eliminarProductoMarketplace(Long id) {
        return productoMarketplaceDAO.eliminarProductoMarketplace(id);
    }
}
