package mx.gob.sedatu.dgdusv.sniiv.utilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class HttpPostMultipart {
    private final String boundary;
    private static final String LINE = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;

    /**
     * This constructor initializes a new HTTP POST request with content type
     * is set to multipart/form-data
     *
     * @param requestURL
     * @param charset
     * @param headers
     * @throws IOException
     */
    public HttpPostMultipart(String requestURL, String charset, Map<String, String> headers) throws IOException {
        this.charset = charset;
        boundary = UUID.randomUUID().toString();
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);    // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        if (headers != null && headers.size() > 0) {
            Iterator<String> it = headers.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = headers.get(key);
                httpConn.setRequestProperty(key, value);
            }
        }
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
    }

    /**
     * Adds a form field to the request
     *
     * @param name  field name
     * @param value field value
     */
    public void addFormField(String name, String value) {
        writer.append("--" + boundary).append(LINE);
        writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append(LINE);
        writer.append("Content-Type: text/plain; charset=" + charset).append(LINE);
        writer.append(LINE);
        writer.append(value).append(LINE);
        writer.flush();
    }

    /**
     * Adds a upload file section to the request
     *
     * @param fieldName
     * @param uploadFile
     * @throws IOException
     */
    public void addFilePart(String fieldName, MultipartFile uploadFile,String name)
            throws IOException {
        writer.append("--" + boundary).append(LINE);
        writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + name + "\"").append(LINE);
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(name)).append(LINE);
        writer.append("Content-Transfer-Encoding: binary").append(LINE);
        writer.append(LINE);
        writer.flush();

        outputStream.write(uploadFile.getBytes(), 0, (int)uploadFile.getSize());

        outputStream.flush();
        writer.append(LINE);
        writer.flush();
    }

    /**
     * Completes the request and receives response from the server.
     *
     * @return String as response in case the server returned
     * status OK, otherwise an exception is thrown.
     * @throws IOException
     */
    public String finish() throws IOException {
        writer.flush();
        writer.append("--" + boundary + "--").append(LINE);
        writer.close();

        // checks server's status code first
        int status = httpConn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED) {
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(httpConn.getInputStream(), "utf-8"))) {
                StringBuilder res = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    res.append(responseLine.trim());
                }
                br.close();
                httpConn.getInputStream().close();
                httpConn.disconnect();
                return res.toString();
            }
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
    }
}
