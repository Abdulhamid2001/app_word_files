package uz.ds.app_word_file.service;//package uz.ds.wordfile.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ds.app_word_file.entity.Attachment;
import uz.ds.app_word_file.entity.AttachmentContent;
import uz.ds.app_word_file.payload.DownloadFile;
import uz.ds.app_word_file.payload.ReqTableValues;
import uz.ds.app_word_file.repository.AttachmentContentRepository;
import uz.ds.app_word_file.repository.AttachmentRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class WordTableService {
    @Autowired
    AttachmentService attachmentService;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public DownloadFile createTableWord(ReqTableValues reqTableValues) throws IOException {
            XWPFDocument document = new XWPFDocument();
//        FileOutputStream out = new FileOutputStream("word.docx");

            XWPFTable table = document.createTable();

            XWPFTableRow tableRowOne = table.getRow(0);
            tableRowOne.getCell(0).setText("id");
            tableRowOne.addNewTableCell().setText("firstName");
            tableRowOne.addNewTableCell().setText("lastName");


            XWPFTableRow tableRowTwo = table.createRow();
            tableRowTwo.getCell(0).setText("1");
            tableRowTwo.getCell(1).setText(reqTableValues.firstName);
            tableRowTwo.getCell(2).setText(reqTableValues.lastName);


            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            String encode=null;
            document.write(baos);
            document.close();
            baos.close();

            encode= Base64.encodeBase64String(baos.toByteArray());

            assert encode!=null;
            Attachment attachment=new Attachment(
                    "word.docx",
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                    (long) encode.length()
            );
            Attachment savedAttachment=attachmentRepository.save(attachment);
            AttachmentContent savedAttachmentContent=new AttachmentContent(savedAttachment,baos.toByteArray());
            attachmentContentRepository.save(savedAttachmentContent);

            return new DownloadFile(savedAttachment.getName(),savedAttachment.getId());
    }


}
