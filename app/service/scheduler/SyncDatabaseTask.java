package service.scheduler;
import javax.inject.Inject;

import akka.actor.ActorSystem;
import model.marketplace.ProductoMarketplace;
import model.proveedores.ProductoProveedores;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;
import service.producto.marketplace.ProductoMarketplaceService;
import service.producto.proveedores.ProductoProveedoresService;

import java.util.concurrent.TimeUnit;

public class SyncDatabaseTask {
    @Inject
    private ProductoProveedoresService productoProveedoresService;

    @Inject
    private ProductoMarketplaceService productoMarketplaceService;

    private final ActorSystem actorSystem;
    private final ExecutionContext executionContext;

    @Inject
    public SyncDatabaseTask(ActorSystem actorSystem, ExecutionContext executionContext) {
        this.actorSystem = actorSystem;
        this.executionContext = executionContext;

        this.initialize();
    }

    private void initialize() {
        this.actorSystem.scheduler().schedule(
                Duration.create(10, TimeUnit.SECONDS), // initialDelay
                Duration.create(15, TimeUnit.MINUTES), // interval
                () -> {
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
                        });
                    }, this.executionContext);
                },
                this.executionContext
        );
    }
}
