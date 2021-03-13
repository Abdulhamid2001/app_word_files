package uz.ds.app_word_file.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ds.app_word_file.service.HtmlToPdfService;

@RestController
@RequestMapping("/api/html")
public class HtmlToPdfController {
    @Autowired
    HtmlToPdfService htmlToPdfService;
    @GetMapping("/{fileName}")
    public HttpEntity<byte[]> htmlToPdf(@PathVariable String fileName){
        return htmlToPdfService.htmlToPdf(fileName);
    }
}
