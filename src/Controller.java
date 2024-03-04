import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class МойКонтроллер {
    private List<Товар> товары = new ArrayList<>();

    // Главная страница
    @GetMapping("/")
    public String главнаяСтраница(Model модель) {
        модель.addAttribute("товары", товары);
        return "index";
    }

    // Добавление товара
    @PostMapping("/добавитьТовар")
    public String добавитьТовар(@ModelAttribute Товар товар) {
        товар.setId(товары.size() + 1);
        товары.add(товар);
        return "redirect:/";
    }

    // Просмотр списка всех товаров
    @GetMapping("/товары")
    public String списокТоваров(Model модель) {
        модель.addAttribute("товары", товары);
        return "productList";
    }

    // Добавление товара в корзину
    @PostMapping("/добавитьВКорзину/{id}")
    public String добавитьВКорзину(@PathVariable int id) {
        Товар товар = товары.stream().filter(т -> т.getId() == id).findFirst().orElse(null);
        if (товар != null) {
            товар.setКоличествоВКорзине(товар.getКоличествоВКорзине() + 1);
            товар.setКоличествоВОстатке(товар.getКоличествоВОстатке() - 1);
        }
        return "redirect:/товары";
    }
}