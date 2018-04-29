package dao.proveedores;

import dao.DatabaseExecutionContext;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import model.proveedores.ProductoProveedores;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductoProveedoresDAO {
    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public ProductoProveedoresDAO(DatabaseExecutionContext executionContext, EbeanConfig ebeanConfig){

        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    public CompletionStage<List<ProductoProveedores>> getProductosProveedores(){
        return supplyAsync(() -> {
            return ebeanServer.find(ProductoProveedores.class).findList();
        }, executionContext);
    }
}
