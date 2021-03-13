package uz.ds.app_word_file.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;


@Service
public class DownloadLinkService {

    public HttpEntity<byte[]> downloadFile() {
        File file = new File("d:/bootCamp/projects/word.docx");
        byte[] bytes = new byte[(int) file.length()];
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream("d:/bootCamp/projects/word.docx")))) {
            dataInputStream.readFully(bytes);
        } catch (Exception e) {
            System.out.println(e);
        }
    //    System.out.println(Arrays.toString(bytes));



        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + file);
        header.setContentLength(bytes.length);

        return new HttpEntity<byte[]>(bytes,header);


    }

}
