/*     */ package javax.activation;
/*     */ 
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PipedInputStream;
/*     */ import java.io.PipedOutputStream;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataHandler
/*     */   implements Transferable
/*     */ {
/*  64 */   private DataSource dataSource = null;
/*  65 */   private DataSource objDataSource = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private Object object = null;
/*  71 */   private String objectMimeType = null;
/*     */ 
/*     */   
/*  74 */   private CommandMap currentCommandMap = null;
/*     */ 
/*     */   
/*  77 */   private static final DataFlavor[] emptyFlavors = new DataFlavor[0];
/*  78 */   private DataFlavor[] transferFlavors = emptyFlavors;
/*     */ 
/*     */   
/*  81 */   private DataContentHandler dataContentHandler = null;
/*  82 */   private DataContentHandler factoryDCH = null;
/*     */ 
/*     */   
/*  85 */   private static DataContentHandlerFactory factory = null;
/*  86 */   private DataContentHandlerFactory oldFactory = null;
/*     */   
/*  88 */   private String shortType = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataHandler(DataSource ds) {
/*  99 */     this.dataSource = ds;
/* 100 */     this.oldFactory = factory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataHandler(Object obj, String mimeType) {
/* 113 */     this.object = obj;
/* 114 */     this.objectMimeType = mimeType;
/* 115 */     this.oldFactory = factory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataHandler(URL url) {
/* 126 */     this.dataSource = new URLDataSource(url);
/* 127 */     this.oldFactory = factory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized CommandMap getCommandMap() {
/* 134 */     if (this.currentCommandMap != null) {
/* 135 */       return this.currentCommandMap;
/*     */     }
/* 137 */     return CommandMap.getDefaultCommandMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataSource getDataSource() {
/* 155 */     if (this.dataSource == null) {
/*     */       
/* 157 */       if (this.objDataSource == null)
/* 158 */         this.objDataSource = new DataHandlerDataSource(this); 
/* 159 */       return this.objDataSource;
/*     */     } 
/* 161 */     return this.dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 173 */     if (this.dataSource != null) {
/* 174 */       return this.dataSource.getName();
/*     */     }
/* 176 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContentType() {
/* 187 */     if (this.dataSource != null) {
/* 188 */       return this.dataSource.getContentType();
/*     */     }
/* 190 */     return this.objectMimeType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() throws IOException {
/* 218 */     InputStream ins = null;
/*     */     
/* 220 */     if (this.dataSource != null) {
/* 221 */       ins = this.dataSource.getInputStream();
/*     */     } else {
/* 223 */       DataContentHandler dch = getDataContentHandler();
/*     */       
/* 225 */       if (dch == null) {
/* 226 */         throw new UnsupportedDataTypeException("no DCH for MIME type " + 
/* 227 */             getBaseType());
/*     */       }
/* 229 */       if (dch instanceof ObjectDataContentHandler && (
/* 230 */         (ObjectDataContentHandler)dch).getDCH() == null) {
/* 231 */         throw new UnsupportedDataTypeException("no object DCH for MIME type " + 
/* 232 */             getBaseType());
/*     */       }
/*     */       
/* 235 */       final DataContentHandler fdch = dch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 243 */       final PipedOutputStream pos = new PipedOutputStream();
/* 244 */       PipedInputStream pin = new PipedInputStream(pos);
/* 245 */       (new Thread(new Runnable()
/*     */           {
/*     */             public void run() {
/*     */               
/* 249 */               try { fdch.writeTo(DataHandler.this.object, DataHandler.this.objectMimeType, pos); }
/* 250 */               catch (IOException iOException)
/*     */               
/*     */               { 
/*     */                 try {
/* 254 */                   pos.close();
/* 255 */                 } catch (IOException iOException1) {} } finally { try { pos.close(); } catch (IOException iOException) {} }
/*     */ 
/*     */             
/*     */             }
/* 259 */           })).start();
/* 260 */       ins = pin;
/*     */     } 
/*     */     
/* 263 */     return ins;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeTo(OutputStream os) throws IOException {
/* 283 */     if (this.dataSource != null) {
/* 284 */       InputStream is = null;
/* 285 */       byte[] data = new byte[8192];
/*     */ 
/*     */       
/* 288 */       is = this.dataSource.getInputStream();
/*     */       try {
/*     */         int bytes_read;
/* 291 */         while ((bytes_read = is.read(data)) > 0) {
/* 292 */           os.write(data, 0, bytes_read);
/*     */         }
/*     */       } finally {
/* 295 */         is.close();
/* 296 */         is = null;
/*     */       } 
/*     */     } else {
/* 299 */       DataContentHandler dch = getDataContentHandler();
/* 300 */       dch.writeTo(this.object, this.objectMimeType, os);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream getOutputStream() throws IOException {
/* 318 */     if (this.dataSource != null) {
/* 319 */       return this.dataSource.getOutputStream();
/*     */     }
/* 321 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized DataFlavor[] getTransferDataFlavors() {
/* 349 */     if (factory != this.oldFactory) {
/* 350 */       this.transferFlavors = emptyFlavors;
/*     */     }
/*     */     
/* 353 */     if (this.transferFlavors == emptyFlavors)
/* 354 */       this.transferFlavors = getDataContentHandler().getTransferDataFlavors(); 
/* 355 */     if (this.transferFlavors == emptyFlavors) {
/* 356 */       return this.transferFlavors;
/*     */     }
/* 358 */     return (DataFlavor[])this.transferFlavors.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDataFlavorSupported(DataFlavor flavor) {
/* 374 */     DataFlavor[] lFlavors = getTransferDataFlavors();
/*     */     
/* 376 */     for (int i = 0; i < lFlavors.length; i++) {
/* 377 */       if (lFlavors[i].equals(flavor))
/* 378 */         return true; 
/*     */     } 
/* 380 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
/* 418 */     return getDataContentHandler().getTransferData(flavor, this.dataSource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void setCommandMap(CommandMap commandMap) {
/* 434 */     if (commandMap != this.currentCommandMap || commandMap == null) {
/*     */       
/* 436 */       this.transferFlavors = emptyFlavors;
/* 437 */       this.dataContentHandler = null;
/*     */       
/* 439 */       this.currentCommandMap = commandMap;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandInfo[] getPreferredCommands() {
/* 457 */     if (this.dataSource != null) {
/* 458 */       return getCommandMap().getPreferredCommands(getBaseType(), this.dataSource);
/*     */     }
/*     */     
/* 461 */     return getCommandMap().getPreferredCommands(getBaseType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandInfo[] getAllCommands() {
/* 477 */     if (this.dataSource != null) {
/* 478 */       return getCommandMap().getAllCommands(getBaseType(), this.dataSource);
/*     */     }
/* 480 */     return getCommandMap().getAllCommands(getBaseType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandInfo getCommand(String cmdName) {
/* 496 */     if (this.dataSource != null) {
/* 497 */       return getCommandMap().getCommand(getBaseType(), cmdName, this.dataSource);
/*     */     }
/*     */     
/* 500 */     return getCommandMap().getCommand(getBaseType(), cmdName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getContent() throws IOException {
/* 521 */     if (this.object != null) {
/* 522 */       return this.object;
/*     */     }
/* 524 */     return getDataContentHandler().getContent(getDataSource());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getBean(CommandInfo cmdinfo) {
/* 540 */     Object bean = null;
/*     */ 
/*     */ 
/*     */     
/* 544 */     try { ClassLoader cld = null;
/*     */       
/* 546 */       cld = SecuritySupport.getContextClassLoader();
/* 547 */       if (cld == null)
/* 548 */         cld = getClass().getClassLoader(); 
/* 549 */       bean = cmdinfo.getCommandObject(this, cld); }
/* 550 */     catch (IOException iOException) {  }
/* 551 */     catch (ClassNotFoundException classNotFoundException) {}
/*     */     
/* 553 */     return bean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized DataContentHandler getDataContentHandler() {
/* 576 */     if (factory != this.oldFactory) {
/* 577 */       this.oldFactory = factory;
/* 578 */       this.factoryDCH = null;
/* 579 */       this.dataContentHandler = null;
/* 580 */       this.transferFlavors = emptyFlavors;
/*     */     } 
/*     */     
/* 583 */     if (this.dataContentHandler != null) {
/* 584 */       return this.dataContentHandler;
/*     */     }
/* 586 */     String simpleMT = getBaseType();
/*     */     
/* 588 */     if (this.factoryDCH == null && factory != null) {
/* 589 */       this.factoryDCH = factory.createDataContentHandler(simpleMT);
/*     */     }
/* 591 */     if (this.factoryDCH != null) {
/* 592 */       this.dataContentHandler = this.factoryDCH;
/*     */     }
/* 594 */     if (this.dataContentHandler == null) {
/* 595 */       if (this.dataSource != null) {
/* 596 */         this
/* 597 */           .dataContentHandler = getCommandMap().createDataContentHandler(simpleMT, this.dataSource);
/*     */       } else {
/* 599 */         this
/* 600 */           .dataContentHandler = getCommandMap().createDataContentHandler(simpleMT);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 605 */     if (this.dataSource != null) {
/* 606 */       this.dataContentHandler = new DataSourceDataContentHandler(this.dataContentHandler, this.dataSource);
/*     */     }
/*     */     else {
/*     */       
/* 610 */       this.dataContentHandler = new ObjectDataContentHandler(this.dataContentHandler, this.object, this.objectMimeType);
/*     */     } 
/*     */ 
/*     */     
/* 614 */     return this.dataContentHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized String getBaseType() {
/* 622 */     if (this.shortType == null) {
/* 623 */       String ct = getContentType();
/*     */       try {
/* 625 */         MimeType mt = new MimeType(ct);
/* 626 */         this.shortType = mt.getBaseType();
/* 627 */       } catch (MimeTypeParseException e) {
/* 628 */         this.shortType = ct;
/*     */       } 
/*     */     } 
/* 631 */     return this.shortType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void setDataContentHandlerFactory(DataContentHandlerFactory newFactory) {
/* 649 */     if (factory != null) {
/* 650 */       throw new Error("DataContentHandlerFactory already defined");
/*     */     }
/* 652 */     SecurityManager security = System.getSecurityManager();
/* 653 */     if (security != null)
/*     */       
/*     */       try {
/* 656 */         security.checkSetFactory();
/* 657 */       } catch (SecurityException ex) {
/*     */ 
/*     */ 
/*     */         
/* 661 */         if (DataHandler.class.getClassLoader() != newFactory
/* 662 */           .getClass().getClassLoader()) {
/* 663 */           throw ex;
/*     */         }
/*     */       }  
/* 666 */     factory = newFactory;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\DataHandler.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */