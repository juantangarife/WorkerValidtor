package service.producto.proveedores;

import dao.proveedores.ProductoProveedoresDAO;
import model.proveedores.ProductoProveedores;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class ProductoProveedoresServiceImpl implements ProductoProveedoresService {

    private final ProductoProveedoresDAO productoProveedoresDAO;

    @Inject
    public ProductoProveedoresServiceImpl(ProductoProveedoresDAO productoProveedoresDAO){
        this.productoProveedoresDAO = productoProveedoresDAO;
    }

    public CompletionStage<List<ProductoProveedores>> getProductosProveedores(){
        return productoProveedoresDAO.getProductosProveedores();
    }

}
