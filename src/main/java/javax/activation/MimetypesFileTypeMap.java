/*     */ package javax.activation;
/*     */ 
/*     */ import com.sun.activation.registries.LogSupport;
/*     */ import com.sun.activation.registries.MimeTypeFile;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Vector;
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
/*     */ 
/*     */ 
/*     */ public class MimetypesFileTypeMap
/*     */   extends FileTypeMap
/*     */ {
/*     */   private MimeTypeFile[] DB;
/*     */   private static final int PROG = 0;
/*     */   private static final String defaultType = "application/octet-stream";
/*     */   private static final String confDir;
/*     */   
/*     */   static {
/*  72 */     String dir = null;
/*     */     try {
/*  74 */       dir = AccessController.<String>doPrivileged(new PrivilegedAction<String>()
/*     */           {
/*     */             public String run() {
/*  77 */               String home = System.getProperty("java.home");
/*  78 */               String newdir = home + home + "conf";
/*  79 */               File conf = new File(newdir);
/*  80 */               if (conf.exists()) {
/*  81 */                 return newdir + newdir;
/*     */               }
/*  83 */               return home + home + "lib" + File.separator;
/*     */             }
/*     */           });
/*  86 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  89 */     confDir = dir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimetypesFileTypeMap() {
/*  96 */     Vector<MimeTypeFile> dbv = new Vector(5);
/*  97 */     MimeTypeFile mf = null;
/*  98 */     dbv.addElement(null);
/*     */     
/* 100 */     LogSupport.log("MimetypesFileTypeMap: load HOME");
/*     */     try {
/* 102 */       String user_home = System.getProperty("user.home");
/*     */       
/* 104 */       if (user_home != null) {
/* 105 */         String path = user_home + user_home + ".mime.types";
/* 106 */         mf = loadFile(path);
/* 107 */         if (mf != null)
/* 108 */           dbv.addElement(mf); 
/*     */       } 
/* 110 */     } catch (SecurityException securityException) {}
/*     */     
/* 112 */     LogSupport.log("MimetypesFileTypeMap: load SYS");
/*     */     
/*     */     try {
/* 115 */       if (confDir != null) {
/* 116 */         mf = loadFile(confDir + "mime.types");
/* 117 */         if (mf != null)
/* 118 */           dbv.addElement(mf); 
/*     */       } 
/* 120 */     } catch (SecurityException securityException) {}
/*     */     
/* 122 */     LogSupport.log("MimetypesFileTypeMap: load JAR");
/*     */     
/* 124 */     loadAllResources(dbv, "META-INF/mime.types");
/*     */     
/* 126 */     LogSupport.log("MimetypesFileTypeMap: load DEF");
/* 127 */     mf = loadResource("/META-INF/mimetypes.default");
/*     */     
/* 129 */     if (mf != null) {
/* 130 */       dbv.addElement(mf);
/*     */     }
/* 132 */     this.DB = new MimeTypeFile[dbv.size()];
/* 133 */     dbv.copyInto((Object[])this.DB);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private MimeTypeFile loadResource(String name) {
/* 140 */     InputStream clis = null;
/*     */     try {
/* 142 */       clis = SecuritySupport.getResourceAsStream(getClass(), name);
/* 143 */       if (clis != null) {
/* 144 */         MimeTypeFile mf = new MimeTypeFile(clis);
/* 145 */         if (LogSupport.isLoggable()) {
/* 146 */           LogSupport.log("MimetypesFileTypeMap: successfully loaded mime types file: " + name);
/*     */         }
/* 148 */         return mf;
/*     */       } 
/* 150 */       if (LogSupport.isLoggable()) {
/* 151 */         LogSupport.log("MimetypesFileTypeMap: not loading mime types file: " + name);
/*     */       }
/*     */     }
/* 154 */     catch (IOException e) {
/* 155 */       if (LogSupport.isLoggable())
/* 156 */         LogSupport.log("MimetypesFileTypeMap: can't load " + name, e); 
/* 157 */     } catch (SecurityException sex) {
/* 158 */       if (LogSupport.isLoggable())
/* 159 */         LogSupport.log("MimetypesFileTypeMap: can't load " + name, sex); 
/*     */     } finally {
/*     */       try {
/* 162 */         if (clis != null)
/* 163 */           clis.close(); 
/* 164 */       } catch (IOException iOException) {}
/*     */     } 
/* 166 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadAllResources(Vector<MimeTypeFile> v, String name) {
/* 173 */     boolean anyLoaded = false;
/*     */     try {
/*     */       URL[] urls;
/* 176 */       ClassLoader cld = null;
/*     */       
/* 178 */       cld = SecuritySupport.getContextClassLoader();
/* 179 */       if (cld == null)
/* 180 */         cld = getClass().getClassLoader(); 
/* 181 */       if (cld != null) {
/* 182 */         urls = SecuritySupport.getResources(cld, name);
/*     */       } else {
/* 184 */         urls = SecuritySupport.getSystemResources(name);
/* 185 */       }  if (urls != null) {
/* 186 */         if (LogSupport.isLoggable())
/* 187 */           LogSupport.log("MimetypesFileTypeMap: getResources"); 
/* 188 */         for (int i = 0; i < urls.length; i++) {
/* 189 */           URL url = urls[i];
/* 190 */           InputStream clis = null;
/* 191 */           if (LogSupport.isLoggable());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
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
/*     */       }
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
/*     */     }
/* 224 */     catch (Exception ex) {
/* 225 */       if (LogSupport.isLoggable()) {
/* 226 */         LogSupport.log("MimetypesFileTypeMap: can't load " + name, ex);
/*     */       }
/*     */     } 
/*     */     
/* 230 */     if (!anyLoaded) {
/* 231 */       LogSupport.log("MimetypesFileTypeMap: !anyLoaded");
/* 232 */       MimeTypeFile mf = loadResource("/" + name);
/* 233 */       if (mf != null) {
/* 234 */         v.addElement(mf);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private MimeTypeFile loadFile(String name) {
/* 242 */     MimeTypeFile mtf = null;
/*     */     
/*     */     try {
/* 245 */       mtf = new MimeTypeFile(name);
/* 246 */     } catch (IOException iOException) {}
/*     */ 
/*     */     
/* 249 */     return mtf;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimetypesFileTypeMap(String mimeTypeFileName) throws IOException {
/* 260 */     this();
/* 261 */     this.DB[0] = new MimeTypeFile(mimeTypeFileName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimetypesFileTypeMap(InputStream is) {
/* 271 */     this();
/*     */     try {
/* 273 */       this.DB[0] = new MimeTypeFile(is);
/* 274 */     } catch (IOException iOException) {}
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
/*     */   public synchronized void addMimeTypes(String mime_types) {
/* 286 */     if (this.DB[0] == null) {
/* 287 */       this.DB[0] = new MimeTypeFile();
/*     */     }
/* 289 */     this.DB[0].appendToRegistry(mime_types);
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
/*     */   public String getContentType(File f) {
/* 301 */     return getContentType(f.getName());
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
/*     */   public synchronized String getContentType(String filename) {
/* 314 */     int dot_pos = filename.lastIndexOf(".");
/*     */     
/* 316 */     if (dot_pos < 0) {
/* 317 */       return "application/octet-stream";
/*     */     }
/* 319 */     String file_ext = filename.substring(dot_pos + 1);
/* 320 */     if (file_ext.length() == 0) {
/* 321 */       return "application/octet-stream";
/*     */     }
/* 323 */     for (int i = 0; i < this.DB.length; i++) {
/* 324 */       if (this.DB[i] != null) {
/*     */         
/* 326 */         String result = this.DB[i].getMIMETypeString(file_ext);
/* 327 */         if (result != null)
/* 328 */           return result; 
/*     */       } 
/* 330 */     }  return "application/octet-stream";
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\MimetypesFileTypeMap.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */