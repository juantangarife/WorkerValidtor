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
    public Result index() {
        return ok(views.html.index.render());
    }
}
