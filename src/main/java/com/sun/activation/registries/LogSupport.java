/*    */ package com.sun.activation.registries;
/*    */ 
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LogSupport
/*    */ {
/*    */   private static boolean debug = false;
/*    */   private static Logger logger;
/* 22 */   private static final Level level = Level.FINE;
/*    */   
/*    */   static {
/*    */     try {
/* 26 */       debug = Boolean.getBoolean("javax.activation.debug");
/* 27 */     } catch (Throwable throwable) {}
/*    */ 
/*    */     
/* 30 */     logger = Logger.getLogger("javax.activation");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void log(String msg) {
/* 41 */     if (debug)
/* 42 */       System.out.println(msg); 
/* 43 */     logger.log(level, msg);
/*    */   }
/*    */   
/*    */   public static void log(String msg, Throwable t) {
/* 47 */     if (debug)
/* 48 */       System.out.println(msg + "; Exception: " + msg); 
/* 49 */     logger.log(level, msg, t);
/*    */   }
/*    */   
/*    */   public static boolean isLoggable() {
/* 53 */     return (debug || logger.isLoggable(level));
/*    */   }
/*    */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\registries\LogSupport.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */