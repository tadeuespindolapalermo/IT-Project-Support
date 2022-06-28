/*    */ package com.sun.activation.registries;
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
/*    */ public class MimeTypeEntry
/*    */ {
/*    */   private String type;
/*    */   private String extension;
/*    */   
/*    */   public MimeTypeEntry(String mime_type, String file_ext) {
/* 20 */     this.type = mime_type;
/* 21 */     this.extension = file_ext;
/*    */   }
/*    */   
/*    */   public String getMIMEType() {
/* 25 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getFileExtension() {
/* 29 */     return this.extension;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 33 */     return "MIMETypeEntry: " + this.type + ", " + this.extension;
/*    */   }
/*    */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\registries\MimeTypeEntry.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */