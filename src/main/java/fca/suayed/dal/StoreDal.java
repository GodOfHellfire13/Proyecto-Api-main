package fca.suayed.dal;

import fca.suayed.dao.StoreDao;
import fca.suayed.dto.ClientDto; // Importación necesaria
import fca.suayed.dto.ProductDto;
import fca.suayed.dto.ResponseDto;
import fca.suayed.services.JdbiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@ApplicationScoped
public class StoreDal {

    private static final Logger LOGGER = Logger.getLogger(StoreDal.class);

    @Inject
    JdbiService jdbiService;

    // Método existente para productos
    public ResponseDto<List<ProductDto>> getProducts() {
        ResponseDto responseDto = new ResponseDto<List<ProductDto>>();
        responseDto.setSuccess(true);
        Jdbi jdbi = jdbiService.getInstance();
        var products = jdbi.withExtension(StoreDao.class, dao -> dao.getProducts());
        responseDto.setData(products);
        return responseDto;
    }

    // Método existente para agregar productos
    public ResponseDto<String> addProduct(final ProductDto productDto) {
        ResponseDto responseDto = new ResponseDto<String>();
        responseDto.setSuccess(false);

        Jdbi jdbi = jdbiService.getInstance();
        jdbi.useExtension(StoreDao.class, dao -> {
            dao.addProduct(productDto);
            responseDto.setSuccess(true);
            responseDto.setData("ok");
        });

        return responseDto;
    }

    // 1. NUEVO MÉTODO PARA OBTENER CLIENTES (similar a getProducts)
    public ResponseDto<List<ClientDto>> getClients() {
        ResponseDto responseDto = new ResponseDto<List<ClientDto>>();
        responseDto.setSuccess(true);
        
        try {
            Jdbi jdbi = jdbiService.getInstance();
            var clients = jdbi.withExtension(StoreDao.class, dao -> dao.getClients());
            responseDto.setData(clients);
            
            // Opcional: Log para depuración
            LOGGER.infof("Clientes obtenidos: %d", clients.size());
            
        } catch (Exception e) {
            responseDto.setSuccess(false);
            responseDto.setData("Error al obtener clientes");
            LOGGER.error("Error en getClients: " + e.getMessage(), e);
        }
        
        return responseDto;
    }

    // 2. MÉTODO OPCIONAL PARA AGREGAR CLIENTES (siguiendo el patrón de addProduct)
    public ResponseDto<String> addClient(final ClientDto clientDto) {
        ResponseDto responseDto = new ResponseDto<String>();
        responseDto.setSuccess(false);

        try {
            Jdbi jdbi = jdbiService.getInstance();
            jdbi.useExtension(StoreDao.class, dao -> {
                dao.addClient(clientDto);
                responseDto.setSuccess(true);
                responseDto.setData("ok");
            });
        } catch (Exception e) {
            responseDto.setData("Error al agregar cliente: " + e.getMessage());
            LOGGER.error("Error en addClient: " + e.getMessage(), e);
        }

        return responseDto;
    }
}