package service.producto.proveedores;

import com.google.inject.ImplementedBy;
import model.proveedores.ProductoProveedores;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(ProductoProveedoresServiceImpl.class)
public interface ProductoProveedoresService {

    public CompletionStage<List<ProductoProveedores>> getProductosProveedores();

}