package service.scheduler;

import com.google.inject.ImplementedBy;
import model.proveedores.ProductoProveedores;
import service.producto.proveedores.ProductoProveedoresServiceImpl;

import java.util.List;
import java.util.concurrent.CompletionStage;

@ImplementedBy(SyncDatabasesServiceImpl.class)
public interface SyncDatabasesService {

    public CompletionStage<Boolean> syncProductsDatabases();

}