# Documentación Técnica - Práctica 2

## Introducción

Esta documentación describe la evolución y los cambios realizados en la aplicación **ToDoList** durante la implementación de los módulos de **barra de menú**, **listado de usuarios** y **descripción de usuario**.  
El objetivo es que cualquier desarrollador del equipo pueda entender las nuevas funcionalidades, su estructura y los puntos clave del código fuente.

---

## Nuevas Clases y Métodos Implementados

### Controladores

#### `UsuarioController`
- `listadoUsuarios(Model model)`: Gestiona la ruta `/registrados` y añade la lista de usuarios al modelo.
- `descripcionUsuario(@PathVariable Long id, Model model)`: Gestiona la ruta `/registrados/{id}` y añade el usuario correspondiente al modelo.

#### `HomeController`
- `about(Model model, HttpSession session)`: Añade el usuario logeado al modelo para la página `/about`.

### Servicios

#### `UsuarioService`
- `findAll()`: Devuelve la lista de todos los usuarios registrados.
- `findUsuarioEntityById(Long id)`: Devuelve un usuario por su identificador.

---

## Plantillas Thymeleaf Añadidas

- `about.html`: Página "Acerca de", muestra información de la aplicación y utiliza la barra de menú dinámica.
- `listadoUsuarios.html`: Muestra una tabla con todos los usuarios registrados y enlaces a su descripción.
- `descripcionUsuario.html`: Muestra los datos de un usuario específico, excepto la contraseña.
- `fragments.html`: Contiene el fragmento de barra de menú común para todas las páginas (excepto login y registro).

---

## Explicación de los Tests Implementados

- **`MenuBarraWebTest`**: Verifica que la barra de menú muestra los enlaces correctos según si el usuario está logeado o no.
- **`ListadoUsuariosWebTest`**: Comprueba que la página `/registrados` muestra la tabla de usuarios y los encabezados.
- **`DescripcionUsuarioWebTest`**: Comprueba que la página `/registrados/{id}` muestra los datos del usuario seleccionado.

Estos tests aseguran que las rutas principales y la lógica de visualización cumplen los requisitos funcionales y que los cambios no rompen la aplicación.

---

## Explicación de Código Fuente Relevante

### Ejemplo: Fragmento de barra de menú (`fragments.html`)

```html
<div th:fragment="menu(usuario)">
    <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
        <div class="container-fluid">
            <a class="navbar-brand" href="/about">ToDoList</a>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/tareas">Tareas</a>
                    </li>
                    <li class="nav-item dropdown" th:if="${usuario != null}">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
                           role="button" data-bs-toggle="dropdown" aria-expanded="false"
                           th:text="${usuario.nombre}">Usuario</a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Cuenta</a></li>
                            <li><a class="dropdown-item" th:href="@{/logout}">Cerrar sesión [[${usuario.nombre}]]</a></li>
                        </ul>
                    </li>
                    <li class="nav-item" th:if="${usuario == null}">
                        <a class="nav-link" href="/login">Login</a>
                    </li>
                    <li class="nav-item" th:if="${usuario == null}">
                        <a class="nav-link" href="/registro">Registro</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>
### Explicación

Este fragmento define una barra de menú Bootstrap que se adapta dinámicamente según si el usuario está logeado.  
Si hay usuario, muestra su nombre y opciones de cuenta/cerrar sesión; si no, muestra enlaces a login y registro.

---

### Ejemplo: Listado de usuarios (`UsuarioController.java`)

```java
@GetMapping("/registrados")
public String listadoUsuarios(Model model) {
    List<Usuario> usuarios = usuarioService.findAll();
    model.addAttribute("usuarios", usuarios);
    return "listadoUsuarios";
}
### Explicación

Este método del controlador obtiene la lista de usuarios desde el servicio y la pasa a la vista para su renderizado en una tabla.

---

### Conclusión

La implementación de estos módulos mejora la experiencia de usuario y la mantenibilidad del código:

- La **barra de menú** centraliza la navegación.
- El **listado de usuarios** facilita la administración.
- La **descripción de usuario** permite ver detalles individuales.
- Los **tests** aseguran la calidad y robustez de la aplicación tras cada cambio.
