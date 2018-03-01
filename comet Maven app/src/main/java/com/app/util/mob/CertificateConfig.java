package com.app.util.mob;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.Properties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.core.io.ClassPathResource;

import com.xiaoleilu.hutool.http.ssl.SSLSocketFactoryBuilder;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

public class CertificateConfig {

    static ClassPathResource       resource     = null;

    static Properties              props        = null;

    static String                  driverClass  = null;

    private static Log             log          = LogFactory.get();

    public static String           certPassword = "654321";

    public static SSLSocketFactory sSLSocketFactory;

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            // String testString =
            // URLDecoder.decode(CertificateConfig.class.getResource("/").getPath(),
            // "utf-8")
            // + "client.p12";
            String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
            // windows
            // path = path.replace('/', '\\');
            // path = path.replace("file:", "");
            // path = path.replace("classes\\", "");
            // path = path.substring(1);
            // path = path + "\\key\\client.p12";
            // linux
            path = path.replace("file:", ""); // ȥ��file:
            path = path.replace("classes/", ""); // ȥ��class\
            path += "/key/client.p12";
            InputStream instream = new FileInputStream(path);
            keyStore.load(instream, certPassword.toCharArray());
            instream.close();
            KeyManagerFactory kmFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmFactory.init(keyStore, certPassword.toCharArray());

            SSLSocketFactoryBuilder sSLSocketFactoryBuilder = SSLSocketFactoryBuilder.create();
            sSLSocketFactoryBuilder.setKeyManagers(kmFactory.getKeyManagers());
            sSLSocketFactoryBuilder.setProtocol(SSLSocketFactoryBuilder.TLSv1);
            sSLSocketFactory = sSLSocketFactoryBuilder.build();
        } catch (Exception e) {
            log.error(e, "��ȡCA֤�����");
        }

    }

    public static SSLSocketFactory getsSLSocketFactory() {
        return sSLSocketFactory;
    }

    public static void setsSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        CertificateConfig.sSLSocketFactory = sSLSocketFactory;
    }

    public static String getCertPassword() {
        return certPassword;
    }

    public static void setCertPassword(String certPassword) {
        CertificateConfig.certPassword = certPassword;
    }

}
