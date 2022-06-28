/*    */ package com.sun.activation.viewers;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Image;
/*    */ import java.awt.MediaTracker;
/*    */ import java.awt.Panel;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.activation.CommandObject;
/*    */ import javax.activation.DataHandler;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageViewer
/*    */   extends Panel
/*    */   implements CommandObject
/*    */ {
/* 20 */   private ImageViewerCanvas canvas = null;
/*    */ 
/*    */ 
/*    */   
/* 24 */   private Image image = null;
/* 25 */   private DataHandler _dh = null;
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean DEBUG = false;
/*    */ 
/*    */ 
/*    */   
/*    */   public ImageViewer() {
/* 34 */     this.canvas = new ImageViewerCanvas();
/* 35 */     add(this.canvas);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setCommandContext(String verb, DataHandler dh) throws IOException {
/* 42 */     this._dh = dh;
/* 43 */     setInputStream(this._dh.getInputStream());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setInputStream(InputStream ins) throws IOException {
/* 52 */     MediaTracker mt = new MediaTracker(this);
/* 53 */     int bytes_read = 0;
/* 54 */     byte[] data = new byte[1024];
/* 55 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*    */     
/* 57 */     while ((bytes_read = ins.read(data)) > 0)
/* 58 */       baos.write(data, 0, bytes_read); 
/* 59 */     ins.close();
/*    */ 
/*    */     
/* 62 */     this.image = getToolkit().createImage(baos.toByteArray());
/*    */     
/* 64 */     mt.addImage(this.image, 0);
/*    */     
/*    */     try {
/* 67 */       mt.waitForID(0);
/* 68 */       mt.waitForAll();
/* 69 */       if (mt.statusID(0, true) != 8) {
/* 70 */         System.out.println("Error occured in image loading = " + mt
/* 71 */             .getErrorsID(0));
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 76 */     catch (InterruptedException e) {
/* 77 */       throw new IOException("Error reading image data");
/*    */     } 
/*    */     
/* 80 */     this.canvas.setImage(this.image);
/* 81 */     if (this.DEBUG) {
/* 82 */       System.out.println("calling invalidate");
/*    */     }
/*    */   }
/*    */   
/*    */   public void addNotify() {
/* 87 */     super.addNotify();
/* 88 */     invalidate();
/* 89 */     validate();
/* 90 */     doLayout();
/*    */   }
/*    */   
/*    */   public Dimension getPreferredSize() {
/* 94 */     return this.canvas.getPreferredSize();
/*    */   }
/*    */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\viewers\ImageViewer.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */