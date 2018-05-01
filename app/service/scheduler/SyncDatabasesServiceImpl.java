package service.scheduler;

import model.marketplace.ProductoMarketplace;
import model.proveedores.ProductoProveedores;
import service.producto.marketplace.ProductoMarketplaceService;
import service.producto.proveedores.ProductoProveedoresService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class SyncDatabasesServiceImpl implements SyncDatabasesService{
    @Inject
    private ProductoProveedoresService productoProveedoresService;

    @Inject
    private ProductoMarketplaceService productoMarketplaceService;

    public CompletionStage<Boolean> syncProductsDatabases() {
        return productoProveedoresService.getProductosProveedores().thenComposeAsync(productosProveedores -> {
            return productoMarketplaceService.getProductosMarketplace().thenApplyAsync(productosMarketplace -> {
                for (ProductoProveedores productoProveedor : productosProveedores) {
                    boolean existe = false;
                    for (ProductoMarketplace productoMarketplace : productosMarketplace) {
                        if (productoMarketplace.id.equals(productoProveedor.id)) {
                            String descripcionMarketplace = productoMarketplace.descripcion;
                            String descripcionProveedor = productoProveedor.descripcion;
                            Double precioMarketplace = productoMarketplace.precio;
                            Double precioProveedor = productoProveedor.precio;
                            if (!(descripcionMarketplace.equals(descripcionProveedor) && precioMarketplace.equals(precioProveedor))) {
                                productoMarketplaceService.actualizarProductoMarketplace(productoProveedor.id, descripcionProveedor, precioProveedor);
                            }
                            existe = true;
                        }
                    }
                    if (!existe) {
                        productoMarketplaceService.agregarProductoMarketplace(productoProveedor.id, productoProveedor.descripcion, productoProveedor.precio);
                    }
                }

                for (ProductoMarketplace productoMarketplace : productosMarketplace) {
                    boolean existe = false;
                    for (ProductoProveedores productoProveedor : productosProveedores) {
                        existe = existe || productoProveedor.id.equals(productoMarketplace.id);
                    }
                    if (!existe) {
                        productoMarketplaceService.eliminarProductoMarketplace(productoMarketplace.id);
                    }
                }
                return true;
            });
        });
    }
}
