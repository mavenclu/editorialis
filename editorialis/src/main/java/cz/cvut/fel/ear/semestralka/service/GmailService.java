//package cz.cvut.fel.ear.service;
//
//import java.io.*;
//import java.util.Properties;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.internet.*;
//
//import com.google.api.client.auth.oauth2.Credential;
//import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
//import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
//import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
//import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.client.util.Base64;
//import com.google.api.client.util.store.FileDataStoreFactory;
//import com.google.api.services.gmail.Gmail;
//import com.google.api.services.gmail.GmailScopes;
//import com.google.api.services.gmail.model.Message;
//
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//import java.util.List;
//
//public class GmailService {
//
//    private static final String APPLICATION_NAME = "editorialis";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//    private static final String TOKENS_DIRECTORY_PATH = "tokens";
//    private static final String CREDENTIALS_FILE_PATH = "/client_id.json";
//    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_SEND);
//
//    /**
//     * Creates an authorized Credential object.
//     * @param HTTP_TRANSPORT The network HTTP Transport.
//     * @return An authorized Credential object.
//     * @throws IOException If the credentials.json file cannot be found.
//     */
//    public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
//        // Load client secrets.
//        InputStream in = GmailService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//        if (in == null) {
//            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
//        }
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//
//        // Build flow and trigger user authorization request.
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                .setAccessType("offline")
//                .build();
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
//    }
//
//
//    /**
//     * Create a MimeMessage using the parameters provided.
//     *
//     * @param to email address of the receiver
//     * @param from email address of the sender, the mailbox account
//     * @param subject subject of the email
//     * @param bodyText body text of the email
//     * @return the MimeMessage to be used to send email
//     * @throws MessagingException
//     */
//    public static MimeMessage createEmail(String to,
//                                          String from,
//                                          String subject,
//                                          String bodyText)
//            throws MessagingException {
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props, null);
//
//        MimeMessage email = new MimeMessage(session);
//
//        email.setFrom(new InternetAddress(from));
//        email.addRecipient(javax.mail.Message.RecipientType.TO,
//                new InternetAddress(to));
//        email.setSubject(subject);
//        email.setText(bodyText);
//        return email;
//    }
//
//    /**
//     * Create a message from an email.
//     *
//     * @param emailContent Email to be set to raw of message
//     * @return a message containing a base64url encoded email
//     * @throws IOException
//     * @throws MessagingException
//     */
//    public static Message createMessageWithEmail(MimeMessage emailContent)
//            throws MessagingException, IOException {
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        emailContent.writeTo(buffer);
//        byte[] bytes = buffer.toByteArray();
//        String encodedEmail = Base64.encodeBase64URLSafeString(bytes);
//        Message message = new Message();
//        message.setRaw(encodedEmail);
//        return message;
//    }
//
//    /**
//     * Send an email from the user's mailbox to its recipient.
//     *
//     * @param service Authorized Gmail API instance.
//     * @param userId User's email address. The special value "me"
//     * can be used to indicate the authenticated user.
//     * @param emailContent Email to be sent.
//     * @return The sent message
//     * @throws MessagingException
//     * @throws IOException
//     */
//    public static Message sendMessage(Gmail service,
//                                      String userId,
//                                      MimeMessage emailContent)
//            throws MessagingException, IOException {
//        Message message = createMessageWithEmail(emailContent);
//        message = service.users().messages().send(userId, message).execute();
//
//        System.out.println("Message id: " + message.getId());
//        System.out.println(message.toPrettyString());
//        return message;
//    }
//
//    public GmailService() throws GeneralSecurityException, IOException {
//    }
//
//    public static String getApplicationName() {
//        return APPLICATION_NAME;
//    }
//
//    public static JsonFactory getJsonFactory() {
//        return JSON_FACTORY;
//    }
//
//    public static String getTokensDirectoryPath() {
//        return TOKENS_DIRECTORY_PATH;
//    }
//
//    public static String getCredentialsFilePath() {
//        return CREDENTIALS_FILE_PATH;
//    }
//
//    public static List<String> getSCOPES() {
//        return SCOPES;
//    }
//
//}
