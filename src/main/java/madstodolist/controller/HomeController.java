package madstodolist.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import madstodolist.dto.UsuarioData;
import madstodolist.service.UsuarioService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

@GetMapping("/about")
public String about(Model model, HttpSession session) {
    Long idUsuario = (Long) session.getAttribute("idUsuarioLogeado");
    if (idUsuario != null) {
        UsuarioData usuario = usuarioService.findById(idUsuario);
        model.addAttribute("usuario", usuario);
    }
    return "about";
}
}