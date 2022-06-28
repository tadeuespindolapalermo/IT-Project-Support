/*     */ package com.sun.activation.registries;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.StringReader;
/*     */ import java.util.Hashtable;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MimeTypeFile
/*     */ {
/*  17 */   private String fname = null;
/*  18 */   private Hashtable type_hash = new Hashtable<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeTypeFile(String new_fname) throws IOException {
/*  27 */     File mime_file = null;
/*  28 */     FileReader fr = null;
/*     */     
/*  30 */     this.fname = new_fname;
/*     */     
/*  32 */     mime_file = new File(this.fname);
/*     */     
/*  34 */     fr = new FileReader(mime_file);
/*     */     
/*     */     try {
/*  37 */       parse(new BufferedReader(fr));
/*     */     } finally {
/*     */       try {
/*  40 */         fr.close();
/*  41 */       } catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeTypeFile(InputStream is) throws IOException {
/*  48 */     parse(new BufferedReader(new InputStreamReader(is, "iso-8859-1")));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeTypeFile() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MimeTypeEntry getMimeTypeEntry(String file_ext) {
/*  64 */     return (MimeTypeEntry)this.type_hash.get(file_ext);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMIMETypeString(String file_ext) {
/*  74 */     MimeTypeEntry entry = getMimeTypeEntry(file_ext);
/*     */     
/*  76 */     if (entry != null) {
/*  77 */       return entry.getMIMEType();
/*     */     }
/*  79 */     return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToRegistry(String mime_types) {
/*     */     try {
/* 101 */       parse(new BufferedReader(new StringReader(mime_types)));
/* 102 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void parse(BufferedReader buf_reader) throws IOException {
/* 111 */     String line = null, prev = null;
/*     */     
/* 113 */     while ((line = buf_reader.readLine()) != null) {
/* 114 */       if (prev == null) {
/* 115 */         prev = line;
/*     */       } else {
/* 117 */         prev = prev + prev;
/* 118 */       }  int end = prev.length();
/* 119 */       if (prev.length() > 0 && prev.charAt(end - 1) == '\\') {
/* 120 */         prev = prev.substring(0, end - 1);
/*     */         continue;
/*     */       } 
/* 123 */       parseEntry(prev);
/* 124 */       prev = null;
/*     */     } 
/* 126 */     if (prev != null) {
/* 127 */       parseEntry(prev);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void parseEntry(String line) {
/* 134 */     String mime_type = null;
/* 135 */     String file_ext = null;
/* 136 */     line = line.trim();
/*     */     
/* 138 */     if (line.length() == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 142 */     if (line.charAt(0) == '#') {
/*     */       return;
/*     */     }
/*     */     
/* 146 */     if (line.indexOf('=') > 0) {
/*     */       
/* 148 */       LineTokenizer lt = new LineTokenizer(line);
/* 149 */       while (lt.hasMoreTokens()) {
/* 150 */         String name = lt.nextToken();
/* 151 */         String value = null;
/* 152 */         if (lt.hasMoreTokens() && lt.nextToken().equals("=") && lt
/* 153 */           .hasMoreTokens())
/* 154 */           value = lt.nextToken(); 
/* 155 */         if (value == null) {
/* 156 */           if (LogSupport.isLoggable())
/* 157 */             LogSupport.log("Bad .mime.types entry: " + line); 
/*     */           return;
/*     */         } 
/* 160 */         if (name.equals("type")) {
/* 161 */           mime_type = value; continue;
/* 162 */         }  if (name.equals("exts")) {
/* 163 */           StringTokenizer st = new StringTokenizer(value, ",");
/* 164 */           while (st.hasMoreTokens()) {
/* 165 */             file_ext = st.nextToken();
/* 166 */             MimeTypeEntry entry = new MimeTypeEntry(mime_type, file_ext);
/*     */             
/* 168 */             this.type_hash.put(file_ext, entry);
/* 169 */             if (LogSupport.isLoggable()) {
/* 170 */               LogSupport.log("Added: " + entry.toString());
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 177 */       StringTokenizer strtok = new StringTokenizer(line);
/* 178 */       int num_tok = strtok.countTokens();
/*     */       
/* 180 */       if (num_tok == 0) {
/*     */         return;
/*     */       }
/* 183 */       mime_type = strtok.nextToken();
/*     */       
/* 185 */       while (strtok.hasMoreTokens()) {
/* 186 */         MimeTypeEntry entry = null;
/*     */         
/* 188 */         file_ext = strtok.nextToken();
/* 189 */         entry = new MimeTypeEntry(mime_type, file_ext);
/* 190 */         this.type_hash.put(file_ext, entry);
/* 191 */         if (LogSupport.isLoggable())
/* 192 */           LogSupport.log("Added: " + entry.toString()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\tadeu\Downloads\javax.activation-api-1.2.1\!\com\sun\activation\registries\MimeTypeFile.class
 * Java compiler version: 11 (55.0)
 * JD-Core Version:       1.1.3
 */