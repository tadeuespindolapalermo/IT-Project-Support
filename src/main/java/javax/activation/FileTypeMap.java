/*     */ package javax.activation;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Map;
/*     */ import java.util.WeakHashMap;
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
/*     */ public abstract class FileTypeMap
/*     */ {
/*  35 */   private static FileTypeMap defaultMap = null;
/*  36 */   private static Map<ClassLoader, FileTypeMap> map = new WeakHashMap<>();
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
/*     */   public abstract String getContentType(File paramFile);
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
/*     */   public abstract String getContentType(String paramString);
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
/*     */   public static synchronized void setDefaultFileTypeMap(FileTypeMap fileTypeMap) {
/*  73 */     SecurityManager security = System.getSecurityManager();
/*  74 */     if (security != null) {
/*     */       
/*     */       try {
/*  77 */         security.checkSetFactory();
/*  78 */       } catch (SecurityException ex) {
/*     */ 
/*     */ 
/*     */         
/*  82 */         ClassLoader cl = FileTypeMap.class.getClassLoader();
/*  83 */         if (cl == null || cl.getParent() == null || cl != fileTypeMap
/*  84 */           .getClass().getClassLoader()) {
/*  85 */           throw ex;
/*     */         }
/*     */       } 
/*     */     }
/*  89 */     map.remove(SecuritySupport.getContextClassLoader());
/*  90 */     defaultMap = fileTypeMap;
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
/*     */   public static synchronized FileTypeMap getDefaultFileTypeMap() {
/* 103 */     if (defaultMap != null) {
/* 104 */       return defaultMap;
/*     */     }
/*     */     
/* 107 */     ClassLoader tccl = SecuritySupport.getContextClassLoader();
/* 108 */     FileTypeMap def = map.get(tccl);
/* 109 */     if (def == null) {
/* 110 */       def = new MimetypesFileTypeMap();
/* 111 */       map.put(tccl, def);
/*     */     } 
/* 113 */     return def;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\FileTypeMap.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */