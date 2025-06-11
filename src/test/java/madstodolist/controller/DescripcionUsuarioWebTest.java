package madstodolist.controller;

import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import madstodolist.model.Usuario;
import madstodolist.repository.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class DescripcionUsuarioWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setEmail("test@correo.com");
        usuario.setNombre("Test Nombre");
        usuarioRepository.save(usuario);
    }

    @Test
    public void descripcionUsuarioMuestraDatos() throws Exception {
        this.mockMvc.perform(get("/registrados/" + usuario.getId()))
                .andExpect(content().string(containsString("test@correo.com")))
                .andExpect(content().string(containsString("Test Nombre")));
    }
}