package com.lucaswarwick02.terrariasaveeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;

import java.nio.charset.Charset;
import java.util.HexFormat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class HelloApplication extends Application
{
    public static Stage STAGE = null;

    @Override
    public void start( Stage stage ) throws IOException
    {
        STAGE = stage;
        FXMLLoader fxmlLoader = new FXMLLoader( HelloApplication.class.getResource( "hello-view.fxml" ) );
        Scene scene = new Scene( fxmlLoader.load(), 1280, 720 );
        stage.setTitle( "Hello!" );
        stage.setScene( scene );
        stage.setResizable( false );
        stage.show();
    }

    public String toHex (String arg) {
        String combinedHex = String.format("%16x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
        String[] argSplit = combinedHex.split("(?<=\\G.{2})");
        String hex = "";
        for (String s : argSplit) {
            hex += s + " 00 ";
        }
        return hex.toUpperCase();
    }

    public static void main( String[] args )
    {
        launch();
    }
}