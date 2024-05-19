// package com.foodapi.demo.emailApi;

// import com.google.api.client.auth.oauth2.Credential;
// import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
// import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
// import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
// import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
// import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
// import com.google.api.client.googleapis.json.GoogleJsonError;
// import com.google.api.client.googleapis.json.GoogleJsonResponseException;
// import com.google.api.client.http.HttpRequestInitializer;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.gson.GsonFactory;
// import com.google.api.client.util.store.FileDataStoreFactory;
// import com.google.api.services.gmail.Gmail;
// import com.google.api.services.gmail.GmailScopes;
// import com.google.api.services.gmail.model.Message;

// import java.io.ByteArrayOutputStream;
// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.nio.file.Paths;
// import java.security.GeneralSecurityException;
// import java.util.Properties;
// import java.util.Set;

// import javax.mail.MessagingException;
// import javax.mail.Session;
// import javax.mail.internet.AddressException;
// import javax.mail.internet.InternetAddress;
// import javax.mail.internet.MimeMessage;
// import org.apache.commons.codec.binary.Base64;
// import org.springframework.stereotype.Component;
// import static javax.mail.Message.RecipientType.TO;

// /* Class to demonstrate the use of Gmail Send Message API */
// public class SendMessage {

//     public static final String GMAIL_SEND = "letrogthien@gmail.com";
//     private Gmail service;

//     public SendMessage() throws Exception {

//         NetHttpTransport netHttpTransport = GoogleNetHttpTransport.newTrustedTransport();
//         GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
//         service = new Gmail.Builder(netHttpTransport, jsonFactory, getCredentials(netHttpTransport, jsonFactory))
//                 .setApplicationName("Test Mailer")
//                 .build();

//     }

//     private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, GsonFactory jsonFactory) {
//         // Load client secrets.
//         try {
//             InputStream in = SendMessage.class.getResourceAsStream("/secrec.json");
//             if (in == null) {
//                 throw new FileNotFoundException("not found");
//             }
//             GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory.getDefaultInstance(),
//                     new InputStreamReader(in));

//             // Build flow and trigger user authorization request.
//             GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                     HTTP_TRANSPORT, jsonFactory, clientSecrets, Set.of(GmailScopes.GMAIL_SEND))
//                     .setDataStoreFactory(new FileDataStoreFactory(Paths.get("tokens").toFile()))
//                     .setAccessType("offline")
//                     .build();
//             LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//             Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//             return credential;
//         } catch (Exception e) {
//             e.getMessage();
//             e.getStackTrace();
//         }
//         return null;

//     }

//     public void sendMail(String subject, String body, String emailTo)
//             {

//         try {
//             Properties props = new Properties();
//             Session session = Session.getDefaultInstance(props, null);
//             MimeMessage email = new MimeMessage(session);
//             email.setFrom(new InternetAddress("letrogthien@gmail.com"));
//             email.addRecipient(javax.mail.Message.RecipientType.TO,
//                     new InternetAddress(emailTo));
//             email.setSubject(subject);
//             email.setText(body);

//             ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//             email.writeTo(buffer);
//             byte[] rawMessageBytes = buffer.toByteArray();
//             String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
//             Message msg = new Message();
//             msg.setRaw(encodedEmail);
//             // Create send message
//             msg = service.users().messages().send("me", msg).execute();

//         } catch (GoogleJsonResponseException e) {
//             e.getMessage();
//         } catch (Exception e){
//             e.getMessage();
//         }

//     }
// }