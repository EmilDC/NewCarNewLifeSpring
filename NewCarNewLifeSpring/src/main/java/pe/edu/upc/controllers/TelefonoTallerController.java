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

import pe.edu.upc.entities.TelefonoTaller;
import pe.edu.upc.serviceinterfaces.ITelefonoTallerService;

@Controller
@RequestMapping("/telefonotalleres")
public class TelefonoTallerController {

	@Autowired
	private ITelefonoTallerService ttS;
	
	@GetMapping("/new")
	public String newTelefonoTaller(Model model) {
		model.addAttribute("telefonotaller", new TelefonoTaller());
		return "telefonotaller/telefonotaller";
		
	}
	
	@GetMapping("/list")
	public String listaTelefonoTaller(Model model) {
		try {
			model.addAttribute("TelefonoTaller", new TelefonoTaller());
			model.addAttribute("listaTelefonoTalleres", ttS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "telefonotaller/listTelefonoTalleres";
	}
	
	@PostMapping("/save")
	public String saveTelefonoTaller(@ModelAttribute("telefonotaller") @Valid TelefonoTaller telefonotaller, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "telefonotaller/telefonotaller";
		} else {
			int rpta = ttS.insert(telefonotaller);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "telefonotaller/telefonotaller";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("telefonotaller", new TelefonoTaller());
		return "redirect:/telefonotalleres/list";
		
	}
	
}
