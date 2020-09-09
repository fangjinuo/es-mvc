package com.jn.esmvc.service.util;

import com.jn.langx.security.PKIs;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class CustomSSLContext {

    static final String ALIAS = "key";
    public static final char[] EMPTY_CHARS = new char[0];

    public static SSLContext buildSSLContext(String clientPemPath, String clientKeyPath, String password, String rootPemPath) throws Exception {
        return buildSSLContext(new File(clientPemPath), new File(clientKeyPath), password, new File(rootPemPath));
    }

    public static SSLContext buildSSLContext(File clientPemFile, File clientKeyFile, String password, File rootPemFile) throws Exception {
        final KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        KeyManagerFactory keyManagerFactory = null;
        {
            X509Certificate[] rootX509Certificatess = toX509Certificates(rootPemFile);
            int i = 1;
            for (X509Certificate root: rootX509Certificatess) {
                String alias = Integer.toString(i);
                ks.setCertificateEntry(alias, root);
                i++;
            }
        }
        TrustManagerFactory trustManagerFactory  = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(ks);
        {
            PrivateKey privateKey = toPrivateKey(clientKeyFile, password);
            X509Certificate[] clientKeyCertChain = toX509Certificates(clientPemFile);
            if (clientKeyCertChain != null) {
                keyManagerFactory = buildKeyManagerFactory(clientKeyCertChain, KeyManagerFactory.getDefaultAlgorithm(), privateKey, password, keyManagerFactory);
            }
        }

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(trustManagerFactory == null ? null : keyManagerFactory.getKeyManagers(),
                trustManagerFactory == null ? null : trustManagerFactory.getTrustManagers(),null);
        return context;
    }


    public static KeyManagerFactory buildKeyManagerFactory(X509Certificate[] certChainFile,
                                                    String keyAlgorithm, PrivateKey key,
                                                    String keyPassword, KeyManagerFactory kmf)
            throws KeyStoreException, NoSuchAlgorithmException, IOException,
            CertificateException, UnrecoverableKeyException {
        char[] keyPasswordChars = keyStorePassword(keyPassword);
        KeyStore ks = buildKeyStore(certChainFile, key, keyPasswordChars);
        return buildKeyManagerFactory(ks, keyAlgorithm, keyPasswordChars, kmf);
    }

    static KeyStore buildKeyStore(X509Certificate[] certChain, PrivateKey key, char[] keyPasswordChars)
            throws KeyStoreException, NoSuchAlgorithmException,
            CertificateException, IOException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
        ks.setKeyEntry(ALIAS, key, keyPasswordChars, certChain);
        return ks;
    }
    static KeyManagerFactory buildKeyManagerFactory(KeyStore ks,
                                                    String keyAlgorithm,
                                                    char[] keyPasswordChars, KeyManagerFactory kmf)
            throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        // Set up key manager factory to use our key store
        if (kmf == null) {
            kmf = KeyManagerFactory.getInstance(keyAlgorithm);
        }
        kmf.init(ks, keyPasswordChars);

        return kmf;
    }

    static char[] keyStorePassword(String keyPassword) {
        return keyPassword == null ? EMPTY_CHARS : keyPassword.toCharArray();
    }

    public static PKCS8EncodedKeySpec generateKeySpec(char[] password, byte[] key)
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
            InvalidKeyException, InvalidAlgorithmParameterException {

        if (password == null) {
            return new PKCS8EncodedKeySpec(key);
        }

        EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(encryptedPrivateKeyInfo.getAlgName());
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
        SecretKey pbeKey = keyFactory.generateSecret(pbeKeySpec);
        Cipher cipher = Cipher.getInstance(encryptedPrivateKeyInfo.getAlgName());
        cipher.init(2, pbeKey, encryptedPrivateKeyInfo.getAlgParameters());
        PKCS8EncodedKeySpec keySpec = encryptedPrivateKeyInfo.getKeySpec(cipher);
        return keySpec;
    }

    private static PrivateKey getPrivateKeyFromByteBuffer(ByteBuf encodedKeyBuf, String keyPassword)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
            InvalidAlgorithmParameterException, KeyException, IOException {

        byte[] encodedKey = new byte[encodedKeyBuf.readableBytes()];
        encodedKeyBuf.readBytes(encodedKey).release();

        PKCS8EncodedKeySpec encodedKeySpec = generateKeySpec(
                keyPassword == null ? null : keyPassword.toCharArray(), encodedKey);
        return  PKIs.createPrivateKey("RSA", null, encodedKeySpec);
    }

    static PrivateKey toPrivateKey(File keyFile, String keyPassword) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeySpecException,
            InvalidAlgorithmParameterException,
            KeyException, IOException {
        if (keyFile == null) {
            return null;
        }
        return getPrivateKeyFromByteBuffer(PemReader.readPrivateKey(keyFile), keyPassword);
    }

    static X509Certificate[] toX509Certificates(File file) throws CertificateException {
        if (file == null) {
            return null;
        }
        return getCertificatesFromBuffers(PemReader.readCertificates(file));
    }

    static X509Certificate[] getCertificatesFromBuffers(ByteBuf[] certs) throws CertificateException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate[] x509Certs = new X509Certificate[certs.length];

        try {
            for (int i = 0; i < certs.length; i++) {
                InputStream is = new ByteBufInputStream(certs[i], false);
                try {
                    x509Certs[i] = (X509Certificate) cf.generateCertificate(is);
                } finally {
                    try {
                        is.close();
                    } catch (IOException e) {
                        // This is not expected to happen, but re-throw in case it does.
                        throw new RuntimeException(e);
                    }
                }
            }
        } finally {
            for (ByteBuf buf: certs) {
                buf.release();
            }
        }
        return x509Certs;
    }
}
