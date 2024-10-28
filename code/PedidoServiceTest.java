import com.example.alugueldeveiculos.model.PedidoEntity;
import com.example.alugueldeveiculos.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarPedido() {
        PedidoEntity pedido = new PedidoEntity();
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        PedidoEntity createdPedido = pedidoService.criarPedido(pedido);

        assertNotNull(createdPedido);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void testBuscarPedidoPoriD() {
        PedidoEntity pedido = new PedidoEntity();
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));

        Optional<PedidoEntity> foundPedido = pedidoService.buscarPedidoPoriD(1);

        assertTrue(foundPedido.isPresent());
        verify(pedidoRepository, times(1)).findById(1);
    }

    @Test
    void testListarTodosPedidos() {
        PedidoEntity pedido = new PedidoEntity();
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));

        List<PedidoEntity> pedidos = pedidoService.listarTodosPedidos();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    @Test
    void testAtualizarPedido() {
        PedidoEntity pedido = new PedidoEntity();
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        PedidoEntity updatedPedido = pedidoService.atualizarPedido(pedido);

        assertNotNull(updatedPedido);
        verify(pedidoRepository, times(1)).save(pedido);
    }

    @Test
    void testCancelarPedido() {
        doNothing().when(pedidoRepository).deleteById(1);

        pedidoService.cancelarPedido(1);

        verify(pedidoRepository, times(1)).deleteById(1);
    }
}