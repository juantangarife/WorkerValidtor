package controllers;

import model.marketplace.ProductoMarketplace;
import model.proveedores.ProductoProveedores;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import service.producto.marketplace.ProductoMarketplaceService;
import service.producto.proveedores.ProductoProveedoresService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {
    @Inject
    private ProductoProveedoresService productoProveedoresService;

    @Inject
    private ProductoMarketplaceService productoMarketplaceService;

    @Inject
    private HttpExecutionContext httpExecutionContext;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public CompletionStage<Result> index() {
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
                return ok(views.html.index.render());
            });
        }, httpExecutionContext.current());
    }

    public CompletionStage<Result> deleteFromMarketplace() {
        return productoProveedoresService.getProductosProveedores().thenComposeAsync(productosProveedores -> {
            return productoMarketplaceService.getProductosMarketplace().thenApplyAsync(productosMarketplace -> {
                for (ProductoMarketplace productoMarketplace : productosMarketplace) {
                    boolean existe = false;
                    for (ProductoProveedores productoProveedor : productosProveedores) {
                        existe = productoProveedor.id.equals(productoMarketplace.id);
                    }
                    if (!existe) {
                        productoMarketplaceService.eliminarProductoMarketplace(productoMarketplace.id);
                    }
                }

                return ok(views.html.index.render());
            });
        }, httpExecutionContext.current());
    }


}
