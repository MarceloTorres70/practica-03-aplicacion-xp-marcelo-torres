package madstodolist.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuBarraWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void barraMenuSinUsuarioMuestraLoginYRegistro() throws Exception {
        this.mockMvc.perform(get("/about"))
                .andExpect(content().string(containsString("Login")))
                .andExpect(content().string(containsString("Registro")))
                .andExpect(content().string(not(containsString("Cerrar sesión"))));
    }

}