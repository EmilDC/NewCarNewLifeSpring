package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.Cita;
import pe.edu.upc.serviceinterfaces.ICitaService;
import pe.edu.upc.serviceinterfaces.IServicioService;
import pe.edu.upc.serviceinterfaces.IVehiculoService;

@Controller
@RequestMapping("/citas")
public class CitaController {

	/////////////////////////////////////////////////////////////////

	@Autowired
	private ICitaService cS;
	
	@Autowired
	private IServicioService sS;
	
	@Autowired
	private IVehiculoService vS;

    /////////////////////////////////////////////////////////////////

	@GetMapping("/new")
	public String newCita(Model model) {
		model.addAttribute("cita", new Cita());
		model.addAttribute("listaServicios", sS.list());
		model.addAttribute("listaVehiculos", vS.list());
		return "cita/cita";
	}

	@PostMapping("/save")
	public String saveCita(@Valid Cita cita, BindingResult result, Model model, SessionStatus status) throws Exception {

		if (result.hasErrors()) {
			return "cita/cita";
		} else {
			cS.insertar(cita);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listCitas", cS.listar());
		return "/cita/cita";
	}

	@GetMapping("/list")
	public String listCitas(Model model) {
		try {
			model.addAttribute("cita", new Cita());
			model.addAttribute("listCitas", cS.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cita/listCitas";
	}

	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cS.eliminar(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listCitas", cS.listar());
		return "cita/listCitas";
	}

}
