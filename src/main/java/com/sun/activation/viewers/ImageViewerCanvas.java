/*    */ package com.sun.activation.viewers;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageViewerCanvas
/*    */   extends Canvas
/*    */ {
/* 17 */   private Image canvas_image = null;
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
/*    */   
/*    */   public void setImage(Image new_image) {
/* 33 */     this.canvas_image = new_image;
/* 34 */     invalidate();
/* 35 */     repaint();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension getPreferredSize() {
/* 43 */     Dimension d = null;
/*    */     
/* 45 */     if (this.canvas_image == null) {
/*    */       
/* 47 */       d = new Dimension(200, 200);
/*    */     }
/*    */     else {
/*    */       
/* 51 */       d = new Dimension(this.canvas_image.getWidth(this), this.canvas_image.getHeight(this));
/*    */     } 
/* 53 */     return d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void paint(Graphics g) {
/* 61 */     if (this.canvas_image != null)
/* 62 */       g.drawImage(this.canvas_image, 0, 0, this); 
/*    */   }
/*    */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\viewers\ImageViewerCanvas.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */