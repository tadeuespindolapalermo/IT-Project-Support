/*    */ package com.sun.activation.viewers;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.GridLayout;
/*    */ import java.awt.Panel;
/*    */ import java.awt.TextArea;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.activation.CommandObject;
/*    */ import javax.activation.DataHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextViewer
/*    */   extends Panel
/*    */   implements CommandObject
/*    */ {
/* 20 */   private TextArea text_area = null;
/*    */ 
/*    */   
/* 23 */   private File text_file = null;
/* 24 */   private String text_buffer = null;
/*    */   
/* 26 */   private DataHandler _dh = null;
/*    */   
/*    */   private boolean DEBUG = false;
/*    */ 
/*    */   
/*    */   public TextViewer() {
/* 32 */     setLayout(new GridLayout(1, 1));
/*    */     
/* 34 */     this.text_area = new TextArea("", 24, 80, 1);
/*    */     
/* 36 */     this.text_area.setEditable(false);
/*    */     
/* 38 */     add(this.text_area);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCommandContext(String verb, DataHandler dh) throws IOException {
/* 43 */     this._dh = dh;
/* 44 */     setInputStream(this._dh.getInputStream());
/*    */   }
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
/*    */   public void setInputStream(InputStream ins) throws IOException {
/* 57 */     int bytes_read = 0;
/*    */     
/* 59 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 60 */     byte[] data = new byte[1024];
/*    */     
/* 62 */     while ((bytes_read = ins.read(data)) > 0) {
/* 63 */       baos.write(data, 0, bytes_read);
/*    */     }
/* 65 */     ins.close();
/*    */ 
/*    */ 
/*    */     
/* 69 */     this.text_buffer = baos.toString();
/*    */ 
/*    */     
/* 72 */     this.text_area.setText(this.text_buffer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addNotify() {
/* 77 */     super.addNotify();
/* 78 */     invalidate();
/*    */   }
/*    */   
/*    */   public Dimension getPreferredSize() {
/* 82 */     return this.text_area.getMinimumSize(24, 80);
/*    */   }
/*    */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\viewers\TextViewer.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */