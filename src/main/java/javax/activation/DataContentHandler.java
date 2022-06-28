package javax.activation;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.OutputStream;

public interface DataContentHandler {
  DataFlavor[] getTransferDataFlavors();
  
  Object getTransferData(DataFlavor paramDataFlavor, DataSource paramDataSource) throws UnsupportedFlavorException, IOException;
  
  Object getContent(DataSource paramDataSource) throws IOException;
  
  void writeTo(Object paramObject, String paramString, OutputStream paramOutputStream) throws IOException;
}


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\DataContentHandler.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */