package uz.ds.app_word_file.payload;

import lombok.Data;

@Data
public class ReqLogin {
    private String phoneNumber;
    private String password;
}
