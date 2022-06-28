/*     */ package javax.activation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
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
/*     */ public class URLDataSource
/*     */   implements DataSource
/*     */ {
/*  31 */   private URL url = null;
/*  32 */   private URLConnection url_conn = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URLDataSource(URL url) {
/*  42 */     this.url = url;
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
/*     */   public String getContentType() {
/*  57 */     String type = null;
/*     */     
/*     */     try {
/*  60 */       if (this.url_conn == null)
/*  61 */         this.url_conn = this.url.openConnection(); 
/*  62 */     } catch (IOException iOException) {}
/*     */     
/*  64 */     if (this.url_conn != null) {
/*  65 */       type = this.url_conn.getContentType();
/*     */     }
/*  67 */     if (type == null) {
/*  68 */       type = "application/octet-stream";
/*     */     }
/*  70 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  80 */     return this.url.getFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() throws IOException {
/*  90 */     return this.url.openStream();
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
/*     */   public OutputStream getOutputStream() throws IOException {
/* 103 */     this.url_conn = this.url.openConnection();
/*     */     
/* 105 */     if (this.url_conn != null) {
/* 106 */       this.url_conn.setDoOutput(true);
/* 107 */       return this.url_conn.getOutputStream();
/*     */     } 
/* 109 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getURL() {
/* 118 */     return this.url;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\URLDataSource.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */