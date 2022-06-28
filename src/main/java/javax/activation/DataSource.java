package javax.activation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface DataSource {
  InputStream getInputStream() throws IOException;
  
  OutputStream getOutputStream() throws IOException;
  
  String getContentType();
  
  String getName();
}


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\DataSource.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */