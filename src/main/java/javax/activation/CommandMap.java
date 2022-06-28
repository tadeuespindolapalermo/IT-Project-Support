/*     */ package javax.activation;
/*     */ 
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
/*     */ public abstract class CommandMap
/*     */ {
/*  26 */   private static CommandMap defaultCommandMap = null;
/*  27 */   private static Map<ClassLoader, CommandMap> map = new WeakHashMap<>();
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
/*     */   public static synchronized CommandMap getDefaultCommandMap() {
/*  48 */     if (defaultCommandMap != null) {
/*  49 */       return defaultCommandMap;
/*     */     }
/*     */     
/*  52 */     ClassLoader tccl = SecuritySupport.getContextClassLoader();
/*  53 */     CommandMap def = map.get(tccl);
/*  54 */     if (def == null) {
/*  55 */       def = new MailcapCommandMap();
/*  56 */       map.put(tccl, def);
/*     */     } 
/*  58 */     return def;
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
/*     */   public static synchronized void setDefaultCommandMap(CommandMap commandMap) {
/*  70 */     SecurityManager security = System.getSecurityManager();
/*  71 */     if (security != null) {
/*     */       
/*     */       try {
/*  74 */         security.checkSetFactory();
/*  75 */       } catch (SecurityException ex) {
/*     */ 
/*     */ 
/*     */         
/*  79 */         ClassLoader cl = CommandMap.class.getClassLoader();
/*  80 */         if (cl == null || cl.getParent() == null || cl != commandMap
/*  81 */           .getClass().getClassLoader()) {
/*  82 */           throw ex;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/*  87 */     map.remove(SecuritySupport.getContextClassLoader());
/*  88 */     defaultCommandMap = commandMap;
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
/*     */   public abstract CommandInfo[] getPreferredCommands(String paramString);
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
/*     */   public CommandInfo[] getPreferredCommands(String mimeType, DataSource ds) {
/* 116 */     return getPreferredCommands(mimeType);
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
/*     */   public abstract CommandInfo[] getAllCommands(String paramString);
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
/*     */   public CommandInfo[] getAllCommands(String mimeType, DataSource ds) {
/* 144 */     return getAllCommands(mimeType);
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
/*     */   public abstract CommandInfo getCommand(String paramString1, String paramString2);
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
/*     */   public CommandInfo getCommand(String mimeType, String cmdName, DataSource ds) {
/* 173 */     return getCommand(mimeType, cmdName);
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
/*     */   public abstract DataContentHandler createDataContentHandler(String paramString);
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
/*     */   public DataContentHandler createDataContentHandler(String mimeType, DataSource ds) {
/* 205 */     return createDataContentHandler(mimeType);
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
/*     */   public String[] getMimeTypes() {
/* 217 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\javax\activation\CommandMap.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */