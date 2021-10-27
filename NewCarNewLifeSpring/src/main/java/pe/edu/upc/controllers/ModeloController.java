package pe.edu.upc.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Modelo;
import pe.edu.upc.serviceinterfaces.IMarcaService;
import pe.edu.upc.serviceinterfaces.IModeloService;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

	@Autowired
	private IModeloService  mS;
	
	@Autowired
	private IMarcaService maS;
	
	@GetMapping("/new")
	public String newModelo(Model model) {
		model.addAttribute("modelo", new Modelo());
		model.addAttribute("listaMarcas", maS.list());
		return "modelo/modelo";
		
	}
	
	@GetMapping("/list")
	public String listaModelo(Model model) {
		try {
			model.addAttribute("modelo", new Modelo());
			model.addAttribute("listaModelos", mS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "modelo/listModelos";
	}
	
	@PostMapping("/save")
	public String saveModelo(@ModelAttribute @Valid Modelo modelo, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaMarcas", maS.list());
			return "modelo/modelo";
		} else {
			boolean rpta = mS.insert(modelo);
			if(rpta) {
				//model.addAttribute("mensaje", "Ya existe");
				//return "modelo/modelo";
				return "redirect:/modelos/list";
			} else {
				//model.addAttribute("mensaje", "Se guardo correctamente");
				//status.setComplete();
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/modelos/new";
			}
		}
		//model.addAttribute("modelo", new Modelo());
		//return "redirect:/modelos/list";
		
	}
	
	@RequestMapping("/list")
	public String listModelos(Map<String, Object> model) {
		model.put("listaModelos", mS.list());
		return "modelo/listModelos";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Modelo mod) {
		mS.listarId(mod.getIdModelo());
		return "modelo/listModelos";

	}
	
	@RequestMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, RedirectAttributes objRedir) {

		Modelo objPro = mS.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/modelos/list";
		} else {
			model.addAttribute("listaMarcas", maS.list());
			model.addAttribute("modelo", objPro);
			return "modelo/modelo";
		}
	}
	
	
}
