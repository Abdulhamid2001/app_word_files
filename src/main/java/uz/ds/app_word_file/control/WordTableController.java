package uz.ds.app_word_file.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.ds.app_word_file.payload.DownloadFile;
import uz.ds.app_word_file.payload.ReqTableValues;
import uz.ds.app_word_file.service.AttachmentService;
import uz.ds.app_word_file.service.DownloadLinkService;
import uz.ds.app_word_file.service.WordTableService;

import java.io.IOException;


@RestController
@RequestMapping("/api/create")
public class WordTableController {


    @Autowired
    DownloadLinkService downloadLinkService;
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    WordTableService wordTableService;


    @PostMapping("/docs")
    public DownloadFile downloadFile(@RequestBody ReqTableValues reqTableValues) throws IOException {
        return wordTableService.createTableWord(reqTableValues);
    }


    @GetMapping("/downloading")
    public HttpEntity<?> download() {
        return downloadLinkService.downloadFile();
    }
}
