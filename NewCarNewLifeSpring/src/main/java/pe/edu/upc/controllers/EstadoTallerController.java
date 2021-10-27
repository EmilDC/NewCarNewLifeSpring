package pe.edu.upc.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entities.EstadoTaller;
import pe.edu.upc.serviceinterfaces.IEstadoTallerService;

@Controller
@RequestMapping("/estadotalleres")
public class EstadoTallerController {

	@Autowired
	private IEstadoTallerService etS;
	
	@GetMapping("/new")
	public String newEstadoTaller(Model model) {
		model.addAttribute("estadotaller", new EstadoTaller());
		return "estadotaller/estadotaller";
		
	}
	
	@GetMapping("/list")
	public String listaEstadoTaller(Model model) {
		try {
			model.addAttribute("EstadoTaller", new EstadoTaller());
			model.addAttribute("listaEstadoTalleres", etS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "estadotaller/listEstadoTalleres";
	}
	
	@PostMapping("/save")
	public String saveEstadoTaller(@ModelAttribute("estadotaller") @Valid EstadoTaller estadotaller, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "estadotaller/estadotaller";
		} else {
			int rpta = etS.insert(estadotaller);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "estadotaller/estadotaller";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("estadotaller", new EstadoTaller());
		return "redirect:/estadotalleres/list";
		
	}
	
}
