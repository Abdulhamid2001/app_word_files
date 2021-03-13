package uz.ds.app_word_file.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String messageUzb;
    private String messageEng;
    private String messageRus;
    private String messageUzK;
    private boolean success;
    private  String name;
    private UUID id;




    public ApiResponse(String name, UUID id) {
        this.name = name;
        this.id = id;
    }

    public ApiResponse(String messageUzb, String messageEng,String messageRus, String messageUzK, boolean success) {
        this.messageUzb=messageUzb;
        this.messageEng=messageEng;
        this.messageRus=messageRus;
        this.messageUzK=messageUzK;
        this.success=success;
    }
    public ApiResponse(String messageUzb, Boolean success) {
        this.messageUzb = messageUzb;
        this.success = success;
    }

}
