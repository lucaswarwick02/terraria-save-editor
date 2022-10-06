package com.lucaswarwick02.terrariasaveeditor;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HexFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Hex;

public class HelloController
{
    private final byte[] KEY_AND_IV = HexFormat.of().parseHex("6800330079005F006700550079005A00");
    private static Cipher CIPHER = null;


    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws Exception
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(HelloApplication.STAGE);

        if (file != null) {
            byte[] fileBytes = Files.readAllBytes(file.toPath());

            SecretKey aesKey = new SecretKeySpec( KEY_AND_IV, "AES" );

            Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding" );
            cipher.init( Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec( KEY_AND_IV ) );

            byte[] decryptedFile = cipher.doFinal( fileBytes );

            outputDecryption( decryptedFile );
        }
    }

    private void outputDecryption (byte[] decryptedFile) {
        String decryptedString = String.valueOf( Hex.encodeHex( decryptedFile ) );

        if( decryptedString.length() % 2 !=0 ){
            System.err.println("Invlid hex string.");
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < decryptedString.length(); i = i + 2) {
            // Step-1 Split the hex string into two character group
            String s = decryptedString.substring(i, i + 2);
            // Step-2 Convert each character group into integer using valueOf method
            int n = Integer.valueOf(s, 16);
            // Step-3 Cast the integer value to char
            char c = (char) n;
            builder.append(c);
        }

        welcomeText.setText( builder.toString() );
    }
}