package softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuni.pathfinder.model.view.RouteIndexView;
import softuni.pathfinder.service.RouteService;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public String getAllRoutes(Model model){
        List<RouteIndexView> routes = routeService.getAllRoutes();

        model.addAttribute("routes", routes);

        return "routes";
    }
}
