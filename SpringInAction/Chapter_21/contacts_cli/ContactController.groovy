import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Grab("spring-boot-starter-actuator")
@Grab("thymeleaf-spring4")

@Controller
@RequestMapping("/")
class ContactController {

    @Autowired
    ContactRepository contactRepo

    @RequestMapping(method = RequestMethod.GET)
    String home(Map<String, Object> model) {
        List<Contact> contacts = contactRepo.findAll()
        model.putAll([contacts: contacts])
        "home"
    }

    @RequestMapping(method = RequestMethod.POST)
    String submit(Contact contact) {
        contactRepo.save(contact)
        "redirect:/"
    }

}
//spring run *.groovy
//spring run **/*.groovy
//http://localhost:8080/beans
//http://localhost:8080/autoconfig