@RestController
class Hi {
    @RequestMapping("/")
    String hi() {
        "Hi!"
    }
}
// spring run Hi.groovy