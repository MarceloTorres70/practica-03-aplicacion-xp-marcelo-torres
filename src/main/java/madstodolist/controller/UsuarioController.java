package madstodolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import madstodolist.model.Usuario;
import madstodolist.service.UsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registrados")
    public String listadoUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.findAll(); // No hace falta cast
        model.addAttribute("usuarios", usuarios);
        return "listadoUsuarios";
    }

    @GetMapping("/registrados/{id}")
    public String descripcionUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findUsuarioEntityById(id); // Usamos Usuario, no UsuarioData
        model.addAttribute("usuario", usuario);
        return "descripcionUsuario";
    }
}