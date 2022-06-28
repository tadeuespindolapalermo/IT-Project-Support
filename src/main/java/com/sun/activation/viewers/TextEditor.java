/*     */ package com.sun.activation.viewers;
/*     */ import java.awt.Button;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Panel;
/*     */ import java.awt.TextArea;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;

import javax.activation.CommandObject;
/*     */ import javax.activation.DataHandler;
/*     */ 
/*     */ public class TextEditor extends Panel implements CommandObject, ActionListener {
/*  22 */   private TextArea text_area = null;
/*  23 */   private GridBagLayout panel_gb = null;
/*  24 */   private Panel button_panel = null;
/*  25 */   private Button save_button = null;
/*     */   
/*  27 */   private File text_file = null;
/*  28 */   private String text_buffer = null;
/*  29 */   private InputStream data_ins = null;
/*  30 */   private FileInputStream fis = null;
/*     */   
/*  32 */   private DataHandler _dh = null;
/*     */   
/*     */   private boolean DEBUG = false;
/*     */ 
/*     */   
/*     */   public TextEditor() {
/*  38 */     this.panel_gb = new GridBagLayout();
/*  39 */     setLayout(this.panel_gb);
/*     */     
/*  41 */     this.button_panel = new Panel();
/*     */     
/*  43 */     this.button_panel.setLayout(new FlowLayout());
/*  44 */     this.save_button = new Button("SAVE");
/*  45 */     this.button_panel.add(this.save_button);
/*  46 */     addGridComponent(this, this.button_panel, this.panel_gb, 0, 0, 1, 1, 1, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     this.text_area = new TextArea("This is text", 24, 80, 1);
/*     */ 
/*     */     
/*  57 */     this.text_area.setEditable(true);
/*     */     
/*  59 */     addGridComponent(this, this.text_area, this.panel_gb, 0, 1, 1, 2, 1, 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     this.save_button.addActionListener(this);
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
/*     */   private void addGridComponent(Container cont, Component comp, GridBagLayout mygb, int gridx, int gridy, int gridw, int gridh, int weightx, int weighty) {
/*  84 */     GridBagConstraints c = new GridBagConstraints();
/*  85 */     c.gridx = gridx;
/*  86 */     c.gridy = gridy;
/*  87 */     c.gridwidth = gridw;
/*  88 */     c.gridheight = gridh;
/*  89 */     c.fill = 1;
/*  90 */     c.weighty = weighty;
/*  91 */     c.weightx = weightx;
/*  92 */     c.anchor = 10;
/*  93 */     mygb.setConstraints(comp, c);
/*  94 */     cont.add(comp);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCommandContext(String verb, DataHandler dh) throws IOException {
/*  99 */     this._dh = dh;
/* 100 */     setInputStream(this._dh.getInputStream());
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
/*     */   public void setInputStream(InputStream ins) throws IOException {
/* 114 */     byte[] data = new byte[1024];
/* 115 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 116 */     int bytes_read = 0;
/*     */ 
/*     */     
/* 119 */     while ((bytes_read = ins.read(data)) > 0)
/* 120 */       baos.write(data, 0, bytes_read); 
/* 121 */     ins.close();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     this.text_buffer = baos.toString();
/*     */ 
/*     */     
/* 129 */     this.text_area.setText(this.text_buffer);
/*     */   }
/*     */   
/*     */   private void performSaveOperation() {
/* 133 */     OutputStream fos = null;
/*     */     try {
/* 135 */       fos = this._dh.getOutputStream();
/* 136 */     } catch (Exception exception) {}
/*     */     
/* 138 */     String buffer = this.text_area.getText();
/*     */ 
/*     */     
/* 141 */     if (fos == null) {
/* 142 */       System.out.println("Invalid outputstream in TextEditor!");
/* 143 */       System.out.println("not saving!");
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 148 */       fos.write(buffer.getBytes());
/* 149 */       fos.flush();
/* 150 */       fos.close();
/* 151 */     } catch (IOException e) {
/*     */       
/* 153 */       System.out.println("TextEditor Save Operation failed with: " + e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addNotify() {
/* 159 */     super.addNotify();
/* 160 */     invalidate();
/*     */   }
/*     */   
/*     */   public Dimension getPreferredSize() {
/* 164 */     return this.text_area.getMinimumSize(24, 80);
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent evt) {
/* 169 */     if (evt.getSource() == this.save_button)
/*     */     {
/*     */       
/* 172 */       performSaveOperation();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\viewers\TextEditor.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */