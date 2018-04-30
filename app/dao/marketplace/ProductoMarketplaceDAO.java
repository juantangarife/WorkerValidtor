package dao.marketplace;

import dao.DatabaseExecutionContext;
import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Transaction;
import model.marketplace.ProductoMarketplace;
import play.db.ebean.EbeanConfig;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import static java.util.concurrent.CompletableFuture.supplyAsync;

public class ProductoMarketplaceDAO {
    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public ProductoMarketplaceDAO(DatabaseExecutionContext executionContext, EbeanConfig ebeanConfig){
        this.ebeanServer = Ebean.getServer("marketplace");
        this.executionContext = executionContext;
    }

    public CompletionStage<List<ProductoMarketplace>> getProductosMarketplace(){
        return supplyAsync(() -> {
            return ebeanServer.find(ProductoMarketplace.class).findList();
        }, executionContext);
    }

    public CompletionStage<Boolean> agregarProductoMarketplace(Long id, String descripcion, Double precio){
        Transaction txn = ebeanServer.beginTransaction();
        return supplyAsync(() -> {
            boolean response = false;
            try {
                ProductoMarketplace p = new ProductoMarketplace();
                p.id = id;
                p.descripcion = descripcion;
                p.precio = precio;
                ebeanServer.insert(p);
            } finally {
                txn.end();
            }
            return response;
        }, executionContext);
    }

    public CompletionStage<Long> actualizarProductoMarketplace(Long id, String descripcion, Double precio){
        Transaction txn = ebeanServer.beginTransaction();
        return supplyAsync(() -> {
            try {
                ProductoMarketplace p = ebeanServer.find(ProductoMarketplace.class).setId(id).findUnique();
                if (p != null) {
                    p.descripcion = descripcion;
                    p.precio = precio;

                    p.update();
                    txn.commit();
                }
            } finally {
                txn.end();
            }
            return id;
        }, executionContext);
    }

    public CompletionStage<Boolean> eliminarProductoMarketplace(Long id){
        Transaction txn = ebeanServer.beginTransaction();
        return supplyAsync(() -> {
            boolean response = false;
            try {
                ProductoMarketplace p = ebeanServer.find(ProductoMarketplace.class).setId(id).findUnique();
                if (p != null) {
                    p.delete();
                    txn.commit();
                    response = true;
                }
            } finally {
                txn.end();
            }
            return response;
        }, executionContext);
    }
}
