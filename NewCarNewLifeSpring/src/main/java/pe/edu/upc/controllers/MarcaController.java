package pe.edu.upc.controllers;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entities.Marca;
import pe.edu.upc.serviceinterfaces.IMarcaService;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

	@Autowired
	private IMarcaService mS;
	
	@GetMapping("/new")
	public String newMarca(Model model) {
		model.addAttribute("marca", new Marca());
		return "marca/marca";
		
	}
	
	@GetMapping("/list")
	public String listaMarca(Model model) {
		try {
			model.addAttribute("marca", new Marca());
			model.addAttribute("listaMarcas", mS.list());
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "marca/listmarcas";
	}
	
	@PostMapping("/save")
	public String saveMarca(@Valid Marca marca, BindingResult result, Model model, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			return "marca/marca";
		} else {
			int rpta = mS.insert(marca);
			if(rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "marca/marca";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("marca", new Marca());
		return "redirect:/marcas/list";
		
	}
	
	@RequestMapping("/delete")
	public String deleteMarca(Model model, @RequestParam(value="id") Integer id, @ModelAttribute Marca marca) {
		try {
			if(id!=null && id>0) {
				mS.eliminar(id);
				model.addAttribute("mensaje", "Se elimino correctamente");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error al eliminar marca");
		}
		model.addAttribute("marca", marca);
		model.addAttribute("listaMarcas", mS.list());
		
		return "marca/listMarcas";
	}
	
	@RequestMapping("/update/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir ) {
		
		Optional<Marca> marca= mS.listId(id);
		if(marca==null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un error");
			return "marca/marca";
		}else {
			model.addAttribute("marca", marca);
			return "marca/marca";
		}
		
		
	}
	
	@RequestMapping("/search")
	public String findMarca(Model model, @ModelAttribute Marca marca) {
		
		List<Marca> listaMarcas; 
		listaMarcas=mS.findByNameMarca(marca.getNameMarca());
		
		model.addAttribute("marca", marca);
		model.addAttribute("listaCategorias", listaMarcas);
		return "marca/listMarcas";
	}
}
