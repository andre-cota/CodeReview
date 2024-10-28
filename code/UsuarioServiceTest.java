import com.example.alugueldeveiculos.model.UsuarioEntity;
import com.example.alugueldeveiculos.model.enums.UsuarioEnum;
import com.example.alugueldeveiculos.repository.UsuarioRepository;
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


class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsuarios() {
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        List<UsuarioEntity> usuarios = usuarioService.getAllUsuarios();

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testGetUsuarioById() {
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<UsuarioEntity> foundUsuario = usuarioService.getUsuarioById(1);

        assertTrue(foundUsuario.isPresent());
        verify(usuarioRepository, times(1)).findById(1);
    }

    @Test
    void testGetUsuarioByName() {
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.findByNome("John")).thenReturn(Arrays.asList(usuario));

        List<UsuarioEntity> usuarios = usuarioService.getUsuarioByName("John");

        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
        verify(usuarioRepository, times(1)).findByNome("John");
    }

    @Test
    void testSaveUsuario() {
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioEntity savedUsuario = usuarioService.saveUsuario(usuario);

        assertNotNull(savedUsuario);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testGetClientes() {
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.findByRole(UsuarioEnum.CLIENTE)).thenReturn(Arrays.asList(usuario));

        List<UsuarioEntity> clientes = usuarioService.getClientes();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(usuarioRepository, times(1)).findByRole(UsuarioEnum.CLIENTE);
    }

    @Test
    void testGetAgentes() {
        UsuarioEntity usuario = new UsuarioEntity();
        when(usuarioRepository.findByRole(UsuarioEnum.AGENTE)).thenReturn(Arrays.asList(usuario));

        List<UsuarioEntity> agentes = usuarioService.getAgentes();

        assertNotNull(agentes);
        assertEquals(1, agentes.size());
        verify(usuarioRepository, times(1)).findByRole(UsuarioEnum.AGENTE);
    }

    @Test
    void testUpdateUsuario() {
        UsuarioEntity usuario = new UsuarioEntity();
        UsuarioEntity updatedDetails = new UsuarioEntity();
        updatedDetails.setNome("Updated Name");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        UsuarioEntity updatedUsuario = usuarioService.updateUsuario(1, updatedDetails);

        assertNotNull(updatedUsuario);
        assertEquals("Updated Name", updatedUsuario.getNome());
        verify(usuarioRepository, times(1)).findById(1);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testDeleteUsuario() {
        doNothing().when(usuarioRepository).deleteById(1);

        usuarioService.deleteUsuario(1);

        verify(usuarioRepository, times(1)).deleteById(1);
    }
}